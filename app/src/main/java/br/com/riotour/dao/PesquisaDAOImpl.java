package br.com.riotour.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michael on 28/03/2015.
 */
public class PesquisaDAOImpl extends SQLiteOpenHelper implements PesquisaDAO {
    public static final int VERSION = 1;
    public static final String TABLE = "searched";
    public static final String DATABASE = "searches.db";

	/**
	 * Construtor.
	 * @param context Contexto
	 */
    public PesquisaDAOImpl(Context context) {

        //Chamada do construtor que acessa do BD
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Definicao do comando para para criar a tabela
        StringBuffer ddl = new StringBuffer();

	    ddl.append("CREATE TABLE " + TABLE + "( ");
	    ddl.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		ddl.append("search TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP); ");

	    ddl.append("CREATE UNIQUE INDEX ind_search ");
	    ddl.append("ON " + TABLE + "(search)");

        sqLiteDatabase.execSQL(ddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //Executa o comando de destruicao
	    StringBuffer ddl = new StringBuffer();

	    ddl.append("DROP INDEX IF EXIST " + TABLE + ".ind_search; ");
	    ddl.append("DROP TABLE IF EXIST " + TABLE);

        onCreate(sqLiteDatabase);
    }

	@Override
	public void inserirTermo(String termo) {

        ContentValues values = new ContentValues();
        values.put("search", termo);
        getWritableDatabase().insert(TABLE, null, values);
    }

	@Override
	public List<String> obterTermos() {

        Cursor cursor = getReadableDatabase().query(TABLE, null, null, null, null, null, "timestamp DESC");
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String pesquisa = (cursor.getString(1));
            list.add(pesquisa);
        }
        cursor.close();
        return list;
    }

	@Override
	public String obterTermo(String termo) {
		String termoEncontrado = null;

		Cursor cursor = getReadableDatabase().rawQuery("SELECT search FROM " + TABLE + " WHERE search = ?", new String[]{termo});

		if(cursor.getCount() > 0) {
			cursor.moveToFirst();
			termoEncontrado = cursor.getString(cursor.getColumnIndex("search"));
		}

		return termoEncontrado;
	}

	@Override
	public void atualizarTimestamp(String termo) {
		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE " + TABLE + " ");
		sql.append("   SET timestamp = datetime() ");
		sql.append(" WHERE search = ?");

		getReadableDatabase().execSQL(sql.toString(), new String[]{termo});
	}

	@Override
	public void deletarTodosTermos() {

        getWritableDatabase().execSQL("DELETE FROM " + TABLE);
    }
}
