package com.example.ameaasambientais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddAmeaca extends AppCompatActivity {
    AmeacaSQLiteDatabase db;
    EditText txtEndereco, txtData, txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ameaca);

        txtEndereco = findViewById(R.id.txtEndereco);
        txtData = findViewById(R.id.txtData);
        txtDescricao = findViewById(R.id.txtDescricao);

        db = new AmeacaSQLiteDatabase(
                getBaseContext());
    }

    public void addAmeaca (View view){
        Ameaca a = new Ameaca();
        a.setEndereco(txtEndereco.getText().toString());
        a.setData(txtData.getText().toString());
        a.setDescricao(txtDescricao.getText().toString());

        db.addAmeaca(a);
        finish();
    }
}
