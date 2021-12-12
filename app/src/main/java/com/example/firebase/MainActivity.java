package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebase.Model.Person;
import com.example.firebase.Model.PersonDAO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txt_id, txt_nome, txt_idade;
    PersonDAO personDAO  = new PersonDAO();
    Button btn_salvar, btn_buscar;
    ListView ltv_persons;

    ArrayAdapter persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = findViewById(R.id.txt_id);
        txt_nome = findViewById(R.id.txt_name);
        txt_idade = findViewById(R.id.txt_idade);

        btn_salvar = findViewById(R.id.btn_salvar);
        btn_buscar = findViewById(R.id.btn_buscar);

        ltv_persons = findViewById(R.id.ltvPersons);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDAO.insert(
                        new Person(
                                "" + txt_id.getText(),
                                "" + txt_nome.getText(),
                                "" + txt_idade.getText()
                        )
                ).addOnSuccessListener(suc ->{
                    Toast.makeText(MainActivity.this, "Salvo", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                });
            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = personDAO.seach("");

                query.addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Person> lista = new ArrayList<>();

                        for(DataSnapshot obj: dataSnapshot.getChildren()){
                            Log.d("ENTROU", "onDataChange: " +  obj);
                            lista.add(
                                    obj.getValue(Person.class)
                            );
                        }

                        persons = new ArrayAdapter<Person>(MainActivity.this, android.R.layout.simple_list_item_1, lista);
                        ltv_persons.setAdapter(persons);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}