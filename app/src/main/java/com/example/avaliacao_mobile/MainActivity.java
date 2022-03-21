package com.example.avaliacao_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    MyDatabaseHelper myDB;
    ArrayList<String> imovelId, imovelFinalidade, imovelTipo, imovelValor, imovelCep, imovelEndereco;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        imovelId = new ArrayList<>();
        imovelFinalidade = new ArrayList<>();
        imovelTipo = new ArrayList<>();
        imovelValor = new ArrayList<>();
        imovelCep = new ArrayList<>();
        imovelEndereco = new ArrayList<>();

        storeDataInArrays();


        customAdapter = new CustomAdapter(MainActivity.this, imovelId, imovelFinalidade, imovelTipo, imovelValor, imovelCep, imovelEndereco);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemPerfil:
                fragment fragment = new fragment();
                fragment.show(getSupportFragmentManager(), "My Fragment");
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Nada a exibir", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                imovelId.add(cursor.getString(0));
                imovelFinalidade.add(cursor.getString(1));
                imovelTipo.add(cursor.getString(2));
                imovelValor.add(cursor.getString(3));
                imovelCep.add(cursor.getString(4));
                imovelEndereco.add(cursor.getString(5));

            }
        }
    }
}