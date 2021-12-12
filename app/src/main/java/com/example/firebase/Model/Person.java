package com.example.firebase.Model;

public class Person {

    private String uid;
    private String nome;
    private String idade;

    public Person() {
    }

    public Person(String uid, String nome, String idade) {
        this.uid = uid;
        this.nome = nome;
        this.idade = idade;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + uid + '\'' +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                '}';
    }
}
