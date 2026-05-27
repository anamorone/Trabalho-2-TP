package model;
public class Vacina {

    private String nome;
    private String fabricante;

    public Vacina(){

    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome){ 
        this.nome = nome; 
    }

    public String getFabricante(){
        return fabricante; 
    }
    
    public void setFabricante(String fabricante){
        this.fabricante = fabricante; 
    }
}