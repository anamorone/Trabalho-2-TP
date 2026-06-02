package model;
public abstract class Animal {

    private String nome;
    private int idade;
    private Tutor tutor;

    public Animal(){
        
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public abstract String emitirSom();

    @Override
    public String toString(){
        return this.getNome();
    }

}