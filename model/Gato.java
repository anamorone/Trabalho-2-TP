package model;
public class Gato extends Animal{
    
    public Gato(){
        super();
    }

    @Override
    public void emitirSom(){
        System.out.println("Miau!!");
    }
}
