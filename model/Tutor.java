package model;
import java.util.*;

public class Tutor {

    private String nome;
    private int idade;
    private ArrayList<Animal> animal = new ArrayList<>();

    public Tutor(){

    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
