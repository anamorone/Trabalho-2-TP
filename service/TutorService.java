package service;
import java.util.*;
import model.Tutor;

public class TutorService {
    private ArrayList<Tutor> tutores = new ArrayList<>();

    public void cadastrarTutor(Tutor tutor){
        tutores.add(tutor);
    }

    public ArrayList<Tutor> getTutores(){
        return tutores;
    }

    public ArrayList buscarNome(String nome){
        ArrayList<Tutor> resultado = new ArrayList<>();

        for(Tutor t : tutores){
            if(t.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultado.add(t);
            }
        }
    
        return resultado;
    }
}
