package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import model.*;
import service.*;

public class TelaPrincipal extends JFrame {
    private TutorService tutorService = new TutorService();
    private AnimalService animalService = new AnimalService();
    private ConsultaService consultaService = new ConsultaService();
    private RelatorioService relatorioService = new RelatorioService();
    private ArrayList<ServicoClinico> listaServicos = new ArrayList<>();

    private DefaultTableModel modelTutores;
    private DefaultTableModel modelConsultas;
    private DefaultTableModel modelServicos;

    private JComboBox<Tutor> cbTutoresAnimal;
    private JComboBox<Tutor> cbTutoresConsulta;
    private JComboBox<Animal> cbAnimaisConsulta;
    private JComboBox<ServicoClinico> cbServicosConsulta;

    public TelaPrincipal() {
        setTitle("VetClinic - Sistema de Gestão Hospitalar");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarDadosExemplo();
        
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Tutores & Animais", criarPainelTutoresEAnimais());
        abas.addTab("Serviços Clínicos", criarPainelServicos());
        abas.addTab("Agendamento", criarPainelConsultas());
        abas.addTab("Relatórios no Console", criarPainelRelatorios());

        add(abas);
        atualizarComponentesGraficos();
    }

    private void inicializarDadosExemplo() {
        ServicoClinico s1 = new ServicoClinico();
        s1.setNome("Consulta Geral");
        s1.setValor(150.0);
        listaServicos.add(s1);

        Tutor t1 = new Tutor();
        t1.setNome("Mariana Silva");
        t1.setTelefone("99999-1111");
        tutorService.cadastrarTutor(t1);

        Cachorro c1 = new Cachorro();
        c1.setNome("Thor");
        c1.setIdade(3);
        c1.setTutor(t1);
        animalService.cadastrarAnimal(c1);
    }

    private JPanel criarPainelTutoresEAnimais() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        JPanel pFormularios = new JPanel(new GridLayout(2, 1, 5, 5));

        // Formulário Tutor
        JPanel pTutor = new JPanel(new GridLayout(3, 2, 5, 5));
        pTutor.setBorder(BorderFactory.createTitledBorder("Novo Tutor"));
        JTextField txtNomeTutor = new JTextField();
        JTextField txtTelTutor = new JTextField();
        JButton btnSalvarTutor = new JButton("Cadastrar Tutor");
        pTutor.add(new JLabel("Nome:")); pTutor.add(txtNomeTutor);
        pTutor.add(new JLabel("Telefone:")); pTutor.add(txtTelTutor);
        pTutor.add(new JLabel("")); pTutor.add(btnSalvarTutor);

        // Formulário Animal
        JPanel pAnimal = new JPanel(new GridLayout(5, 2, 5, 5));
        pAnimal.setBorder(BorderFactory.createTitledBorder("Novo Animal"));
        cbTutoresAnimal = new JComboBox<>();
        JTextField txtNomeAnimal = new JTextField();
        JTextField txtIdadeAnimal = new JTextField();
        JComboBox<String> cbEspecie = new JComboBox<>(new String[]{"Cachorro", "Gato"});
        JButton btnSalvarAnimal = new JButton("Cadastrar Animal");
        pAnimal.add(new JLabel("Tutor Responsável:")); pAnimal.add(cbTutoresAnimal);
        pAnimal.add(new JLabel("Nome:")); pAnimal.add(txtNomeAnimal);
        pAnimal.add(new JLabel("Idade:")); pAnimal.add(txtIdadeAnimal);
        pAnimal.add(new JLabel("Espécie:")); pAnimal.add(cbEspecie);
        pAnimal.add(new JLabel("")); pAnimal.add(btnSalvarAnimal);

        pFormularios.add(pTutor);
        pFormularios.add(pAnimal);
        painel.add(pFormularios, BorderLayout.WEST);

        // Listagem e Filtro Central
        JPanel pCentro = new JPanel(new BorderLayout(5, 5));
        JPanel pBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtBusca = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        pBusca.add(new JLabel("Filtrar por Nome (Tutor):")); pBusca.add(txtBusca); pBusca.add(btnBuscar);
        pCentro.add(pBusca, BorderLayout.NORTH);

        modelTutores = new DefaultTableModel(new Object[]{"Tutor", "Telefone", "Animais"}, 0);
        JTable tblTutores = new JTable(modelTutores);
        pCentro.add(new JScrollPane(tblTutores), BorderLayout.CENTER);
        painel.add(pCentro, BorderLayout.CENTER);

        // Eventos dos botões baseados nos seus services
        btnSalvarTutor.addActionListener(e -> {
            if (!txtNomeTutor.getText().trim().isEmpty()) {
                Tutor t = new Tutor();
                t.setNome(txtNomeTutor.getText());
                t.setTelefone(txtTelTutor.getText());
                tutorService.cadastrarTutor(t);
                txtNomeTutor.setText(""); txtTelTutor.setText("");
                atualizarComponentesGraficos();
            }
        });

        btnSalvarAnimal.addActionListener(e -> {
            Tutor selecionado = (Tutor) cbTutoresAnimal.getSelectedItem();
            if (selecionado != null && !txtNomeAnimal.getText().trim().isEmpty()) {
                try {
                    int idade = Integer.parseInt(txtIdadeAnimal.getText());
                    String esp = (String) cbEspecie.getSelectedItem();
                    Animal novoAnimal;
                    
            if (esp.equals("Cachorro")) {
                    novoAnimal = new Cachorro();
                } else {
                    novoAnimal = new Gato();
                }

                    novoAnimal.setNome(txtNomeAnimal.getText());
                    novoAnimal.setIdade(idade);
                    novoAnimal.setTutor(selecionado);

                    animalService.cadastrarAnimal(novoAnimal);
                    txtNomeAnimal.setText(""); txtIdadeAnimal.setText("");
                    atualizarComponentesGraficos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Idade deve ser numérica!");
                }
            }
        });

        btnBuscar.addActionListener(e -> {
            ArrayList<Tutor> filtrados = tutorService.buscarNomeTutor(txtBusca.getText());
            modelTutores.setRowCount(0);
            for (Tutor t : filtrados) {
                modelTutores.addRow(new Object[]{t.getNome(), t.getTelefone(), t.getAnimais().toString()});
            }
        });

        return painel;
    }

    private JPanel criarPainelServicos() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        JPanel pForm = new JPanel(new GridLayout(3, 2, 5, 5));
        pForm.setBorder(BorderFactory.createTitledBorder("Gerenciar Serviços"));

        JTextField txtNomeServ = new JTextField();
        JTextField txtValorServ = new JTextField();
        JButton btnSalvar = new JButton("Cadastrar / Alterar");

        pForm.add(new JLabel("Nome do Serviço:")); pForm.add(txtNomeServ);
        pForm.add(new JLabel("Preço Mínimo (R$):")); pForm.add(txtValorServ);
        pForm.add(new JLabel("")); pForm.add(btnSalvar);
        painel.add(pForm, BorderLayout.NORTH);

        modelServicos = new DefaultTableModel(new Object[]{"Descrição do Procedimento", "Valor Cobrado"}, 0);
        JTable tblServicos = new JTable(modelServicos);
        painel.add(new JScrollPane(tblServicos), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNomeServ.getText();
                double valor = Double.parseDouble(txtValorServ.getText());
                boolean existente = false;
                for (ServicoClinico s : listaServicos) {
                    if (s.getNome().equalsIgnoreCase(nome)) {
                        s.setValor(valor);
                        existente = true;
                        break;
                    }
                }
                if (!existente) {
                    ServicoClinico novoServ = new ServicoClinico();
                    novoServ.setNome(nome);
                    novoServ.setValor(valor);
                    listaServicos.add(novoServ);
                }
                txtNomeServ.setText(""); txtValorServ.setText("");
                atualizarComponentesGraficos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados informados.");
            }
        });

        return painel;
    }

    private JPanel criarPainelConsultas() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        JPanel pForm = new JPanel(new GridLayout(6, 2, 5, 5));
        pForm.setBorder(BorderFactory.createTitledBorder("Agendador Hospitalar"));

        cbTutoresConsulta = new JComboBox<>();
        cbAnimaisConsulta = new JComboBox<>();
        cbServicosConsulta = new JComboBox<>();
        JTextField txtData = new JTextField("28/05/2026");
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Agendada", "Realizada"});
        JButton btnAgendar = new JButton("Registrar Agenda");

        pForm.add(new JLabel("Selecione o Tutor:")); pForm.add(cbTutoresConsulta);
        pForm.add(new JLabel("Selecione o Paciente:")); pForm.add(cbAnimaisConsulta);
        pForm.add(new JLabel("Procedimento Clínico:")); pForm.add(cbServicosConsulta);
        pForm.add(new JLabel("Data da Consulta:")); pForm.add(txtData);
        pForm.add(new JLabel("Estado:")); pForm.add(cbStatus);
        pForm.add(new JLabel("Ação:")); pForm.add(btnAgendar);
        painel.add(pForm, BorderLayout.NORTH);

        modelConsultas = new DefaultTableModel(new Object[]{"Tutor", "Paciente", "Serviço", "Data", "Status"}, 0);
        JTable tblConsultas = new JTable(modelConsultas);
        painel.add(new JScrollPane(tblConsultas), BorderLayout.CENTER);

        cbTutoresConsulta.addActionListener(e -> {
            Tutor t = (Tutor) cbTutoresConsulta.getSelectedItem();
            cbAnimaisConsulta.removeAllItems();
            if (t != null) {
                for (Animal a : t.getAnimais()) {
                    cbAnimaisConsulta.addItem(a);
                }
            }
        });

        btnAgendar.addActionListener(e -> {
            Tutor t = (Tutor) cbTutoresConsulta.getSelectedItem();
            Animal a = (Animal) cbAnimaisConsulta.getSelectedItem();
            ServicoClinico s = (ServicoClinico) cbServicosConsulta.getSelectedItem();
            if (t != null && a != null && s != null) {
                // Demonstração da chamada do método SOBRECARREGADO (Overloading) de serviço
                consultaService.cadastrarConsulta(t, a, s, txtData.getText(), (String) cbStatus.getSelectedItem());
                atualizarComponentesGraficos();
            }
        });

        return painel;
    }

    private JPanel criarPainelRelatorios() {
        JPanel painel = new JPanel(new GridBagLayout());
        JPanel pBotoes = new JPanel(new GridLayout(2, 1, 15, 15));
        
        JButton btnRelatorioGeral = new JButton("📋 Disparar Relatório Geral no Terminal");
        JButton btnSonsAnimais = new JButton("🔊 Executar Teste de Sons (Polimorfismo) no Terminal");
        
        pBotoes.add(btnRelatorioGeral);
        pBotoes.add(btnSonsAnimais);
        painel.add(pBotoes);

        // Ações chamando diretamente os println da sua classe RelatorioService
        btnRelatorioGeral.addActionListener(e -> {
            relatorioService.gerarRelatorioGeral(consultaService.getConsultas());
        });

        btnSonsAnimais.addActionListener(e -> {
            relatorioService.testarSonsDosAnimais(animalService.getAnimais());
        });

        return painel;
    }

    private void atualizarComponentesGraficos() {
        // Sincroniza tabelas e caixas de seleção de Tutores
        modelTutores.setRowCount(0);
        cbTutoresAnimal.removeAllItems();
        cbTutoresConsulta.removeAllItems();
        for (Tutor t : tutorService.getTutores()) {
            modelTutores.addRow(new Object[]{t.getNome(), t.getTelefone(), t.getAnimais().toString()});
            cbTutoresAnimal.addItem(t);
            cbTutoresConsulta.addItem(t);
        }

        // Sincroniza Procedimentos Clínicos
        modelServicos.setRowCount(0);
        cbServicosConsulta.removeAllItems();
        for (ServicoClinico s : listaServicos) {
            modelServicos.addRow(new Object[]{s.getNome(), "R$ " + String.format("%.2f", s.getValor())});
            cbServicosConsulta.addItem(s);
        }

        // Sincroniza Agendamentos
        modelConsultas.setRowCount(0);
        for (Consulta c : consultaService.getConsultas()) {
            modelConsultas.addRow(new Object[]{c.getTutor().getNome(), c.getAnimal().getNome(), c.getServico().getNome(), c.getData(), c.getStatus()});
        }
    }
}
