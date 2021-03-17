package com.example.passwordgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.abhinav.passwordgenerator.PasswordGenerator;

public class MainActivity extends AppCompatActivity {
   // Tanımlamalar
    private Veritabani vt;
    int progressLength;
    String generatedPassword;

    Button generateButton;
    Button copyButton;
    Button saveButton;
    Button sifrelistButton;


    String baslik;
    TextView passwordTextView;
    TextView passwordLengthTextView;



    SeekBar passowrdLengthSeekBar;


    Switch upperCaseSwitch;
    Switch lowerCaseSwitch;
    Switch numberSwitch;
    Switch symbolSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sayfa Başlığı
        baslik = "ŞİFRELER";

        // Tasarımla değişkenleri bağlama
        generateButton = findViewById(R.id.buttonGenerate);
        copyButton = findViewById(R.id.buttonCopy);
        sifrelistButton = findViewById(R.id.buttonSifreList);
        saveButton = findViewById(R.id.buttonSifreSave);



        passwordTextView = findViewById(R.id.passwordView);
        passwordLengthTextView = findViewById(R.id.seekBarLength);


        passowrdLengthSeekBar = findViewById(R.id.passwordLength);


        upperCaseSwitch = findViewById(R.id.upperCaseSwitch);
        lowerCaseSwitch = findViewById(R.id.lowerCaseSwitch);
        numberSwitch = findViewById(R.id.numberSwitch);
        symbolSwitch = findViewById(R.id.symbolSwitch);

        vt = new Veritabani(this);

        // Seekbar
        passowrdLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLengthTextView.setText("Uzunluk : " + progress );
                progressLength = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Generate Button
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordGenerator obj = new PasswordGenerator(progressLength,             // To specify password length
                            upperCaseSwitch.isChecked(),                                         // To include upper case Letters
                lowerCaseSwitch.isChecked(),                                       // To include lower case Letters
                symbolSwitch.isChecked(),                                       // To include secial symbols
                numberSwitch.isChecked());                                    // To include numbers (0-9)

                generatedPassword = obj.generatePassword();// Call generatePassword() method te get the password

                passwordTextView.setText(generatedPassword);



            }
        });

        sifrelistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SifreListPage.class);
                intent.putExtra("sayfaBasligi", baslik);
                MainActivity.this.startActivity(intent);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SifrelerDao().sifreEkle(vt,generatedPassword);
                Toast.makeText(MainActivity.this, "Şifre Kaydedildi.", Toast.LENGTH_SHORT).show();

            }
        });

        // Copy Button
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Şifre Kopyalandı", generatedPassword);
                Toast.makeText(MainActivity.this, "Şifre Kopyalandı", Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);
            }
        });
    }
}