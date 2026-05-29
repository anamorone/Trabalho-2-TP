package service;

import model.Consulta;
import model.Animal;
import java.util.List;

public class RelatorioService {
    
    public void gerarRelatorioGeral(List<Consulta> consultas) {
        System.out.println("\n=========================================================================");
        System.out.println("                  RELATÓRIO GERAL: TUTOR x ANIMAL x CONSULTA             ");
        System.out.println("=========================================================================");
        
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada até o momento.");
            System.out.println("=========================================================================");
            return;
        }

        for (Consulta c : consultas) {
            System.out.println("TUTOR: " + c.getTutor().getNome() + " | TELEFONE: " + c.getTutor().getTelefone());
            System.out.println("ANIMAL: " + c.getAnimal().getNome() + " | ESPÉCIE: " + c.getAnimal().getClass().getSimpleName());
            System.out.println("SERVIÇO: " + c.getServico().getNome() + " | VALOR: R$ " + String.format("%.2f", c.getServico().getValor()));
            System.out.println("DATA: " + c.getData() + " | STATUS: " + c.getStatus());
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    public void testarSonsDosAnimais(List<Animal> animais) {
        System.out.println("\n=========================================================================");
        System.out.println("            TESTANDO SONS DOS ANIMAIS                     ");
        System.out.println("=========================================================================");
        
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado para testar.");
            System.out.println("=========================================================================");
            return;
        }

        for (Animal animal : animais) {
            System.out.println("O " + animal.getClass().getSimpleName() + " chamado " + animal.getNome() + " faz: ");
            System.out.println(animal.emitirSom()); // Chamada polimórfica que retorna a String do som
            System.out.println("-------------------------------------------------------------------------");
        }
    }
}