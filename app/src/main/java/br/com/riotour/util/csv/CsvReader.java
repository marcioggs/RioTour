package br.com.riotour.util.csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class CsvReader implements Closeable {
	private final CsvConfigurator configurator;
	private final BufferedReader reader;
	private Keyed keyed = null;
	private final boolean closeIn;

	private CsvReader(CsvConfigurator configurator, InputStream in, boolean closeIn) {
		this.configurator = configurator;
		this.closeIn = closeIn;
		reader = new BufferedReader(new InputStreamReader(in, configurator.charset));
	}

	public CsvReader(CsvConfigurator configurator, File file) throws IOException {
		this(configurator, new FileInputStream(file), true);
	}
	public CsvReader(CsvConfigurator configurator, InputStream in) {
		this(configurator, in, false);
	}

	public CsvReader(InputStream in) {
		this(new CsvConfigurator(), in, false);
	}

	@Override
	public void close() throws IOException {
		if (closeIn) {
			reader.close();
		}
	}

	public Keyed keyed() throws IOException {
		if (keyed == null) {
			keyed = new Keyed();
		}
		return keyed;
	}

	public Iterable<String> next() throws IOException {
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				return null;
			}

			line = line.trim();
			if (line.isEmpty()) { // Empty lines are IGNORED! (Is it conform to CSV format?) //FIXME Check this!
				continue;
			}

			List<String> components = new LinkedList<String>();

			//TODO: Está ocorrendo erro quando existe o caracter " no meio da célula. Ex.: ...;"A obra "O grito" é de 1900";...

			int i = 0;
			while (i < line.length()) {
				if (line.charAt(i) == configurator.quote) {
					StringBuilder c = new StringBuilder();
					int n = i + 1;
					while (true) {
						int j = line.indexOf(configurator.quote, n);
						if (j < 0) {
							String component = line.substring(n);
							c.append(component).append('\n');
							line = reader.readLine();
							n = 0;
							continue;
						}
						if ((j < (line.length() - 1)) && (line.charAt(j + 1) == configurator.quote)) {
							String component = line.substring(n, j);
							c.append(component).append(configurator.quote);
							n = j + 2;
						} else {
							String component = line.substring(n, j);
							c.append(component);
							components.add(c.toString());
							// Next char is ';'
							i = j + 2;
							break;
						}
					}
				} else {
					int j = line.indexOf(configurator.delimiter, i);
					if (j < 0) {
						String component = line.substring(i);
						components.add(component);
						i = line.length();
					} else {
						String component = line.substring(i, j);
						components.add(component);
						i = j + 1;
					}
				}
			}
			return components;
		}
	}

	public final class Keyed {
		private final List<String> keys = new ArrayList<String>();
		private int currentNumber = 0;

		private Keyed() throws IOException {
			Iterable<String> n = CsvReader.this.next();
			if (n == null) {
				throw new IOException("Missing keys header");
			}
			for (String key : n) {
				keys.add(key);
			}
		}

		public Iterable<String> keys() {
			return keys;
		}

		public final class Line {
			private final int number;
			private final Map<String, String> values = new LinkedHashMap<String, String>();
			private Line(List<String> keys, int number, Iterable<String> line) {
				this.number = number;
				int index = 0;
				for (String value : line) {
					if (index == keys.size()) {
						break;
					}
					String key = keys.get(index);
					values.put(key, value);
					index++;
				}
			}
			public String get(String key) {
				return values.get(key);
			}
			public int number() {
				return number;
			}
			@Override
			public String toString() {
				return "#" + (number + 1) + ":" + values;
			}
		}

		public Line next() throws IOException {
			Iterable<String> line = CsvReader.this.next();
			if (line == null) {
				return null;
			}
			int n = currentNumber;
			currentNumber++;
			return new Line(keys, n, line);
		}
	}
}