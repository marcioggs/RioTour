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
public class PesquisaDAO extends SQLiteOpenHelper {
    public static final int VERSION  =   1;
    public static final String TABLE   =   "searched";
    public static final String DATABASE =   "searches.db";


    public PesquisaDAO(Context context) {

        //Chamada do construtor que acessa do BD
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Definicao do comando para para criar a tabela
        String ddl = "CREATE TABLE " + TABLE + "( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"search TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";

        sqLiteDatabase.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //Executa o comando de destruicao
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE);

        onCreate(sqLiteDatabase);
    }

    public boolean inserir(String pesquisa){

        ContentValues values = new ContentValues();
        values.put("search", pesquisa);
        long id = getWritableDatabase().insert(TABLE, null, values);
        return (id != -1) ? true : false;
    }

    public List<String> queryPesquisa() {

        Cursor cursor = getReadableDatabase().query(TABLE, null, null, null, null, null, "timestamp");
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String pesquisa = (cursor.getString(1));
            list.add(pesquisa);
        }
        cursor.close();
        return list;
    }

    public void deleteAll(){

        getWritableDatabase().execSQL("DELETE FROM " + TABLE );
    }
}
