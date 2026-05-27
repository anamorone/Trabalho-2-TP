package model;
import java.util.*;

public class Tutor {

    private String nome;
    private String telefone;
    private ArrayList<Animal> animais = new ArrayList<>();

    public Tutor(){


    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public ArrayList<Animal> getAnimais(){
        return animais;
    }


        
    
}
