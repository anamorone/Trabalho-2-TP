package service;
import java.util.*;
import model.*;

public class ConsultaService {
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public void cadastrarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void cadastrarConsulta(Tutor tutor, Animal animal, ServicoClinico servico, String data, String status) {
        Consulta c = new Consulta();
        c.setTutor(tutor);
        c.setAnimal(animal);
        c.setServico(servico);
        c.setData(data);
        c.setStatus(status);
        consultas.add(c);
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }
}