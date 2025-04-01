package com.example.ameaasambientais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    AmeacaAdapter ameacaAdapter;
    AmeacaSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*db = new AmeacaSQLiteDatabase(
                getBaseContext());*/
        db = new AmeacaSQLiteDatabase(this);

        /*listView = findViewById(R.id.listView);
        ameacaAdapter = new AmeacaAdapter(getBaseContext(), db);
        listView.setAdapter(ameacaAdapter);*/

        listView = findViewById(R.id.listView);
        ameacaAdapter = new AmeacaAdapter(this, db);
        listView.setAdapter(ameacaAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            changeToUpdate(id);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            db.removeAmeaca((Ameaca) ameacaAdapter.getItem(position));
            //ameacaAdapter.notifyDataSetChanged();
            atualizarLista();
            return true;
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        atualizarLista();
    }

    public void atualizarLista(){
        ameacaAdapter.notifyDataSetChanged();
    }

    /*public void changeToAdd(View v){
        Intent it = new Intent(getBaseContext(), AddAmeaca.class);
        startActivity(it);
    }*/

    public void changeToAdd(View v){
        Intent it = new Intent(this, AddAmeaca.class);
        startActivityForResult(it, 1);
    }

    /*public void changeToUpdate(Long id){
        Intent it = new Intent(getBaseContext(), EditAmeaca.class);
        it.putExtra("ID", id);
        startActivity(it);
    }*/

    public void changeToUpdate(Long id){
        Intent it = new Intent(this, EditAmeaca.class);
        it.putExtra("ID", id);
        startActivityForResult(it, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            atualizarLista();
        }
    }
}
