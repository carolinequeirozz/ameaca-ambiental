package com.example.ameaasambientais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditAmeaca extends AppCompatActivity {
    AmeacaSQLiteDatabase db;
    EditText txtEndereco, txtData, txtDescricao;
    Ameaca current;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ameaca);
        txtEndereco = findViewById(R.id.txtEndereco);
        txtData = findViewById(R.id.txtData);
        txtDescricao = findViewById(R.id.txtDescricao);

        db = new AmeacaSQLiteDatabase(
                getBaseContext());

        Long id = getIntent().getLongExtra("ID", 0);
        current = db.getAmeaca(id);

        txtEndereco.setText(current.getEndereco());
        txtData.setText(current.getData());
        txtDescricao.setText(current.getDescricao());
    }

    public void updateAmeaca(View v){
        current.setEndereco(txtEndereco.getText().toString());
        current.setData(txtData.getText().toString());
        current.setDescricao(txtDescricao.getText().toString());

        db.updateAmeaca(current);
        finish();
    }
}
