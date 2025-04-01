package com.example.ameaasambientais;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class AmeacaSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "ameacas.db";
    public static final Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public AmeacaSQLiteDatabase(Context ctx){
        this.ctx = ctx;
        db = new AmeacaSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class AmeacaTable implements BaseColumns{
        public static final String TABLE_NAME = "ameaca";
        public static final String COLUMN_ENDERECO = "endereco";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_DESCRICAO = "descricao";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_ENDERECO      + " TEXT, " +
                    COLUMN_DATA          + " TEXT, " +
                    COLUMN_DESCRICAO     + " TEXT)";
            return sql;
        }
    }

    public Long addAmeaca(Ameaca a){
        ContentValues values = new ContentValues();
        values.put(AmeacaTable.COLUMN_ENDERECO, a.getEndereco());
        values.put(AmeacaTable.COLUMN_DATA, a.getData());
        values.put(AmeacaTable.COLUMN_DESCRICAO, a.getDescricao());

        return db.insert(AmeacaTable.TABLE_NAME, null, values);
    }

    public Ameaca getAmeaca(Long id){
        String cols[] = {AmeacaTable._ID, AmeacaTable.COLUMN_ENDERECO, AmeacaTable.COLUMN_DATA, AmeacaTable.COLUMN_DESCRICAO};
        String args[] = {id.toString()};
        Cursor cursor = db.query(AmeacaTable.TABLE_NAME, cols, AmeacaTable._ID+"=?", args, null, null, AmeacaTable._ID);

        if(cursor.getCount() != 1){
            return null;
        }

        cursor.moveToNext();
        Ameaca a = new Ameaca();
        a.setId(cursor.getLong(cursor.getColumnIndex(AmeacaTable._ID)));
        a.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_ENDERECO)));
        a.setData(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_DATA)));
        a.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_DESCRICAO)));

        return a;
    }

    public List<Ameaca> getAmeacas(){
        String cols[] = {AmeacaTable._ID, AmeacaTable.COLUMN_ENDERECO, AmeacaTable.COLUMN_DATA, AmeacaTable.COLUMN_DESCRICAO};
        Cursor cursor = db.query(AmeacaTable.TABLE_NAME, cols, null, null, null, null, AmeacaTable.COLUMN_ENDERECO);
        List<Ameaca> ameacas = new ArrayList<>();
        Ameaca a;

        while(cursor.moveToNext()){
            a = new Ameaca();
            a.setId(cursor.getLong(cursor.getColumnIndex(AmeacaTable._ID)));
            a.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_ENDERECO)));
            a.setData(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_DATA)));
            a.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacaTable.COLUMN_DESCRICAO)));
            ameacas.add(a);
        }

        return ameacas;
    }

    public Integer removeAmeaca(Ameaca a){
        String args[] = {a.getId().toString()};
        return db.delete(AmeacaTable.TABLE_NAME, AmeacaTable._ID + "=?", args);
    }

    public Integer updateAmeaca(Ameaca a){
        String args[] = {a.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(AmeacaTable.COLUMN_ENDERECO, a.getEndereco());
        values.put(AmeacaTable.COLUMN_DATA, a.getData());
        values.put(AmeacaTable.COLUMN_DESCRICAO, a.getDescricao());
        return db.update(AmeacaTable.TABLE_NAME, values, AmeacaTable._ID + "=?", args);
    }

    private class AmeacaSQLiteDatabaseHelper extends SQLiteOpenHelper{
        public AmeacaSQLiteDatabaseHelper(){
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(AmeacaTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + AmeacaTable.TABLE_NAME);
            onCreate(db);
        }
    }
}
