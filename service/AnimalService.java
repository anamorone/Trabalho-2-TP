package service;
import model.Animal;
import model.Tutor;
import java.util.*;

public class AnimalService {
    private ArrayList<Animal> animais = new ArrayList<>();

    public void cadastrarAnimal(Animal animal){
        animais.add(animal);
        animal.getTutor().adicionarAnimal(animal);
    }

    public ArrayList<Animal> getAnimais(){
        return animais;
    }

    public ArrayList<Animal> buscarNomeAnimal(String nome){
        ArrayList<Animal> resultado = new ArrayList<>();
        
        for (Animal a : animais){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(a);
            }
        }
        
        return resultado;
    
    }
    
}
