package br.com.riotour.util.csv;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

public final class CsvConfigurator {
    Charset charset = Charsets.UTF_8;
    char delimiter = ',';
    char quote = '"';

    public CsvConfigurator() {
    }

    public CsvConfigurator withDelimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public CsvConfigurator withQuote(char quote) {
        this.quote = quote;
        return this;
    }

    public CsvConfigurator withCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

}