package com.example.firebase.Model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonDAO {

    private DatabaseReference databaseReference;

    public PersonDAO() {
        //INTACIA O BD
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        //PEGA REFÊNCIA
        this.databaseReference = db.getReference();
    }

    public Task<Void> insert(Person person){
        //INSERE NO BD E RETORNA SE SUCESSO
        return databaseReference.child(Person.class.getSimpleName()).child(person.getUid()).setValue(person);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query seach(String nome){
        ArrayList<Person> persons = new ArrayList<>();
        Query query;

        if(nome.equals("")){
            query = databaseReference.child("Person");
        }
        else{
            query = databaseReference.child("Person").orderByChild("nome").startAt(nome).endAt(nome + "\uf8ff");
        }

        return query;
    }
}
