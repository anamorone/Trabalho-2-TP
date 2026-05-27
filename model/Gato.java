package model;
public class Gato extends Animal{
    
    public Gato(){
        super();
    }

    @Override
    public String emitirSom(){
        return "Miau!!";
    }
}
