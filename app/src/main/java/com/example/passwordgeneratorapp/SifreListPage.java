package com.example.passwordgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SifreListPage extends AppCompatActivity {
    RecyclerView rv;
    TextView baslikTextView;
    private ArrayList<Sifreler>sifrelerArrayList;
    private SifrelerAdapter sifrelerAdapter;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_list_page);

        Intent intent = getIntent();
        String baslik = intent.getStringExtra("sayfaBasligi"); //alınan veri String'e çevirilerek aynı tipli data değişkenine atanıyor.

        baslikTextView = findViewById(R.id.sifreSayfasıBaslik);
        baslikTextView.setText(baslik);

        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));



        vt = new Veritabani(this);

        sifrelerArrayList = new SifrelerDao().tumSifreler(vt);


        sifrelerAdapter = new SifrelerAdapter(this, sifrelerArrayList,vt);
        rv.setAdapter(sifrelerAdapter);
    }
}