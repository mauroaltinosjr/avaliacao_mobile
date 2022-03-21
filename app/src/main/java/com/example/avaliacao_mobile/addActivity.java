package com.example.avaliacao_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.avaliacao_mobile.model.CEP;
import com.example.avaliacao_mobile.service.HTTPService;

import java.util.concurrent.ExecutionException;

public class addActivity extends AppCompatActivity {

    EditText et_imovelFinalidade, et_imovelTipo, et_imovelValor, et_imovelCep;
    Button bt_addImovel, bt_buscaCep;
    TextView tv_resposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_imovelFinalidade = findViewById(R.id.et_imovelFinalidade);
        et_imovelTipo = findViewById(R.id.et_imovelTipo);
        et_imovelValor = findViewById(R.id.et_imovelValor);
        et_imovelCep = findViewById(R.id.et_imovelCep);
        bt_addImovel = findViewById(R.id.bt_cadastra);
        bt_buscaCep = findViewById(R.id.bt_buscaCep);
        tv_resposta = findViewById(R.id.tv_respostaWs);


        bt_addImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(addActivity.this);
                myDB.addImovel(et_imovelFinalidade.getText().toString().trim(),
                        et_imovelTipo.getText().toString().trim(),
                        Double.valueOf(et_imovelValor.getText().toString().trim()),
                        Integer.valueOf(et_imovelCep.getText().toString().trim()),
                        tv_resposta.getText().toString().trim());
            }
        });

        bt_buscaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_imovelCep.getText().toString().length() > 0 && !et_imovelCep.getText().toString().equals("") && et_imovelCep.getText().toString().length() == 8){
                    HTTPService service = new HTTPService(et_imovelCep.getText().toString());
                    try {
                        CEP retorno = service.execute().get();
                        tv_resposta.setText(retorno.toString());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }catch (ExecutionException e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    
}

