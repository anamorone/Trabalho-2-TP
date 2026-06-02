package service;

import model.Consulta;
import model.Animal;
import java.util.List;

public class RelatorioService {
    
    public String gerarRelatorioGeral(List<Consulta> consultas) {
        String r = "=========================================================================\n" +
                   "                  RELATÓRIO GERAL: TUTOR x ANIMAL x CONSULTA             \n" +
                   "=========================================================================\n\n";
        
        if (consultas.isEmpty()) {
            r += "Nenhuma consulta registrada até o momento.\n" +
                 "=========================================================================\n";
            return r;
        }

        for (Consulta c : consultas) {
            r += "TUTOR: " + c.getTutor().getNome() + " | TELEFONE: " + c.getTutor().getTelefone() + "\n";
            r += "ANIMAL: " + c.getAnimal().getNome() + " | ESPÉCIE: " + c.getAnimal().getClass().getSimpleName() + "\n";
            r += "SERVIÇO: " + c.getServico().getNome() + " | VALOR: R$ " + String.format("%.2f", c.getServico().getValor()) + "\n";
            r += "DATA: " + c.getData() + " | STATUS: " + c.getStatus() + "\n";
            r += "-------------------------------------------------------------------------\n";
        }
        return r;
    }

    public String testarSonsDosAnimais(List<Animal> animais) {
        String r = "=========================================================================\n" +
                   "            TESTANDO SONS DOS ANIMAIS              \n" +
                   "=========================================================================\n\n";
        
        if (animais.isEmpty()) {
            r += "Nenhum animal cadastrado para testar.\n" +
                 "=========================================================================\n";
            return r;
        }

        for (Animal animal : animais) {
            r += "O " + animal.getClass().getSimpleName() + " chamado " + animal.getNome() + " faz: \n";
            r += " " + animal.emitirSom() + "\n";
            r += "-------------------------------------------------------------------------\n";
        }
        return r;
    }
}