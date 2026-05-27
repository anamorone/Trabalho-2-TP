package model;
public class Cachorro extends Animal{
    public Cachorro(){
        super();
    }

    @Override
    public void emitirSom(){
        System.out.println("Auau!!");
    }
}
