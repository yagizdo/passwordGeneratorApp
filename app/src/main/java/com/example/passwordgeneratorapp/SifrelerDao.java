package com.example.passwordgeneratorapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SifrelerDao {
    public ArrayList<Sifreler> tumSifreler(Veritabani vt) {
        ArrayList<Sifreler> sifrelerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM sifreler",null);

        while (c.moveToNext()) {
             Sifreler s = new Sifreler(c.getInt(c.getColumnIndex("sifre_id")),
                     c.getString(c.getColumnIndex("sifre")));
             sifrelerArrayList.add(s);
        }

        db.close();
        return sifrelerArrayList;

    }

    public void sifreSil(Veritabani vt, int sifre_id) {
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("sifreler","sifre_id=?",new String[]{String.valueOf(sifre_id)});
        db.close();
    }

    public void sifreEkle(Veritabani vt, String sifre) {
        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("sifre",sifre);

        db.insertOrThrow("sifreler",null,values);
        db.close();
    }
}
