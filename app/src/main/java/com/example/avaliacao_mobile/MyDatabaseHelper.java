package com.example.avaliacao_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper  extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DB_imobiliaria";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "imovel";
    private static final String COLUMN_ID = "imovelId";
    private static final String COLUMN_FINALIDADE = "imovelFinalidade";
    private static final String COLUMN_TIPO = "imovelTipo";
    private static final String COLUMN_VALOR = "imovelValor";
    private static final String COLUMN_CEP = "imovelCep";
    private static final String COLUMN_ENDERECO = "imovelEndereco";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FINALIDADE + " TEXT, " +
                COLUMN_TIPO + " TEXT, " +
                COLUMN_VALOR + " DOUBLE, " +
                COLUMN_CEP + " INTEGER," +
                COLUMN_ENDERECO + " TEXT); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addImovel(String finalidade, String tipo, Double valor, int cep, String endereco) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FINALIDADE, finalidade);
        cv.put(COLUMN_TIPO, tipo);
        cv.put(COLUMN_VALOR, valor);
        cv.put(COLUMN_CEP, cep);
        cv.put(COLUMN_ENDERECO, endereco);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Imóvel adicionado com sucesso", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

