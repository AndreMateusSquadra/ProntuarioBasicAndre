package andre.mateus;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe principal
 */
public class ProntuarioApp extends JFrame {
    private JTextField txtNomePaciente;
    private JTextField txtCpfPaciente;
    private JTextField txtNomeMedico;
    private JTextField txtEspecialidade;
    private JTextField txtDataConsulta;
    private JTextArea txtDescricaoDiagnostico;
    private JTextArea txtPrescricaoMedicamentos;
    private ProntuarioManager prontuarioManager;

    // Cores
    private static final Color BACKGROUND_COLOR = new Color(34, 40, 49);
    private static final Color FOREGROUND_COLOR = new Color(238, 238, 238);
    private static final Color BUTTON_COLOR = new Color(0, 122, 255);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color FIELD_BACKGROUND = new Color(47, 54, 64);
    private static final Color FIELD_BORDER_COLOR = new Color(100, 110, 120);

    public ProntuarioApp() {
        prontuarioManager = new ProntuarioManager();
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        // Configuração do painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBackground(BACKGROUND_COLOR);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Painel de formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBackground(BACKGROUND_COLOR);
        
        // Campos do formulário com estilo moderno e cores escuras
        painelFormulario.add(criarCampoTexto("Nome do Paciente"));
        painelFormulario.add(criarCampoTexto("CPF do Paciente"));
        painelFormulario.add(criarCampoTexto("Nome do Médico"));
        painelFormulario.add(criarCampoTexto("Especialidade"));
        painelFormulario.add(criarCampoTexto("Data da Consulta"));
        
        // Áreas de texto com estilo moderno e cores escuras
        painelFormulario.add(criarAreaTexto("Descrição do Diagnóstico"));
        painelFormulario.add(criarAreaTexto("Prescrição de Medicamentos"));

        // Adicionar o painel de formulário a um JScrollPane
        JScrollPane scrollPaneFormulario = new JScrollPane(painelFormulario);
        scrollPaneFormulario.setBorder(null); // Remover a borda do scroll pane se desejar um visual mais limpo
        scrollPaneFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));
        painelBotoes.setBackground(BACKGROUND_COLOR);
        
        JButton btnSalvar = criarBotao("Salvar Prontuário");
        JButton btnLimpar = criarBotao("Limpar Campos");
        JButton btnExibir = criarBotao("Exibir Prontuários");

        painelBotoes.add(Box.createHorizontalGlue());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(Box.createRigidArea(new Dimension(15, 0)));
        painelBotoes.add(btnLimpar);
        painelBotoes.add(Box.createRigidArea(new Dimension(15, 0)));
        painelBotoes.add(btnExibir);
        painelBotoes.add(Box.createHorizontalGlue());

        // Adicionando painéis ao painel principal
        painelPrincipal.add(scrollPaneFormulario, BorderLayout.CENTER); // Adicionar o scroll pane em vez do painelFormulario
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Adicionando painel principal à janela
        add(painelPrincipal);

        // Configurando ações dos botões
        btnSalvar.addActionListener(e -> salvarProntuario());
        btnLimpar.addActionListener(e -> limparCampos());
        btnExibir.addActionListener(e -> exibirProntuarios());
    }

    private JPanel criarCampoTexto(String label) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(BACKGROUND_COLOR);
        
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel.setForeground(FOREGROUND_COLOR); // Usar cor clara para o texto
        
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(FIELD_BACKGROUND); // Fundo escuro para o campo
        field.setForeground(FOREGROUND_COLOR); // Texto claro dentro do campo
        field.setCaretColor(FOREGROUND_COLOR); // Cor do cursor
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(FIELD_BORDER_COLOR), // Borda clara
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        panel.add(jLabel, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(0, 15)), BorderLayout.SOUTH);
        
        this.txtNomePaciente = field; // Atribui o campo à variável da classe
        if (label.equals("CPF do Paciente")) this.txtCpfPaciente = field;
        if (label.equals("Nome do Médico")) this.txtNomeMedico = field;
        if (label.equals("Especialidade")) this.txtEspecialidade = field;
        if (label.equals("Data da Consulta")) this.txtDataConsulta = field;

        return panel; // Retorna o painel
    }

    private JPanel criarAreaTexto(String label) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(BACKGROUND_COLOR);
        
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel.setForeground(FOREGROUND_COLOR); // Usar cor clara para o texto
        
        JTextArea area = new JTextArea(3, 20);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setBackground(FIELD_BACKGROUND); // Fundo escuro para a área de texto
        area.setForeground(FOREGROUND_COLOR); // Texto claro dentro da área
        area.setCaretColor(FOREGROUND_COLOR); // Cor do cursor
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(FIELD_BORDER_COLOR), // Borda clara
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(null); // Remover borda do scroll pane para combinar com o campo
        scrollPane.getViewport().setBackground(FIELD_BACKGROUND); // Fundo do viewport também escuro
        
        panel.add(jLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(0, 15)), BorderLayout.SOUTH);

        if (label.equals("Descrição do Diagnóstico")) this.txtDescricaoDiagnostico = area; // Atribui a área à variável da classe
        if (label.equals("Prescrição de Medicamentos")) this.txtPrescricaoMedicamentos = area;
        
        return panel; // retorna o painel comlpeto
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botao.setBackground(BUTTON_COLOR);
        botao.setForeground(BUTTON_TEXT_COLOR);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // efeito Hover
        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(BUTTON_COLOR.darker());
            }
            public void mouseExited(MouseEvent e) {
                botao.setBackground(BUTTON_COLOR);
            }
        });
        
        return botao;
    }

    private void configurarJanela() {
        setTitle("Sistema de Prontuários Médicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setBackground(BACKGROUND_COLOR); // Definir fundo da janela
    }

    private void salvarProntuario() {
        Prontuario prontuario = new Prontuario(
            txtNomePaciente.getText(),
            txtCpfPaciente.getText(),
            txtNomeMedico.getText(),
            txtEspecialidade.getText(),
            txtDataConsulta.getText(),
            txtDescricaoDiagnostico.getText(),
            txtPrescricaoMedicamentos.getText()
        );

        if (!prontuarioManager.validarProntuario(prontuario)) {
            JOptionPane.showMessageDialog(this,
                "Por favor, preencha todos os campos!",
                "Erro de Validação",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            prontuarioManager.salvarProntuario(prontuario);
            JOptionPane.showMessageDialog(this,
                "Prontuário salvo com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar prontuário: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNomePaciente.setText("");
        txtCpfPaciente.setText("");
        txtNomeMedico.setText("");
        txtEspecialidade.setText("");
        txtDataConsulta.setText("");
        txtDescricaoDiagnostico.setText("");
        txtPrescricaoMedicamentos.setText("");
    }

    private void exibirProntuarios() {
        try {
            String prontuarios = prontuarioManager.listarProntuarios();
            JTextArea areaTexto = new JTextArea(prontuarios);
            areaTexto.setEditable(false);
            areaTexto.setBackground(FIELD_BACKGROUND); // Fundo escuro para a área de texto de exibição
            areaTexto.setForeground(FOREGROUND_COLOR); // Texto claro na área de exibição
            areaTexto.setCaretPosition(0); // Rolar para o topo
            JScrollPane scrollPane = new JScrollPane(areaTexto);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            scrollPane.getViewport().setBackground(FIELD_BACKGROUND); // Fundo do viewport escuro

            JDialog dialog = new JDialog(this, "Prontuários Cadastrados", true);
            dialog.add(scrollPane);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.getContentPane().setBackground(BACKGROUND_COLOR); // Fundo do diálogo escuro
            dialog.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao ler prontuários: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Aplica o Look and Feel Nimbus para um projeto com bastante boiolagem!
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se Nimbus não estiver disponível, usa o L&F padrão do sistema ou ignora
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Tratar exceção ou ignorar
            }
        }

        SwingUtilities.invokeLater(() -> {
            new ProntuarioApp().setVisible(true);
        });
    }
} 