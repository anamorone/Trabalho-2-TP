package model;
public class ServicoClinico {

    private String nome;
    private double valor;

    public ServicoClinico(){

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    @Override
    public String toString(){
        return nome + " (R$ " + String.format("%.2f", valor) + ")";
    }

}
