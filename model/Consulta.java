package model;
public class Consulta {
    private Tutor tutor;
    private Animal animal;
    private ServicoClinico servico;
    private String data;
    private String status;

    public Consulta(){

    }

    public Tutor getTutor(){
        return tutor;
    }

    public void setTutor(Tutor tutor){
        this.tutor = tutor;
    }

    public Animal getAnimal(){
        return animal;
    }

    public void setAnimal(Animal animal){
        this.animal = animal;
    }

    public ServicoClinico getServico(){
        return servico;
    }

    public void setServico(ServicoClinico servico){
        this.servico = servico;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
