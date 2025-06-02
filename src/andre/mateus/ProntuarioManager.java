package andre.mateus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar as operações de arquivo dos prontuários
 */
public class ProntuarioManager {
    private static final String ARQUIVO_PRONTUARIOS = "prontuarios.txt";

    /**
     * Salva um prontuário no arquivo
     */
    public void salvarProntuario(Prontuario prontuario) throws IOException {
        try (FileWriter fw = new FileWriter(ARQUIVO_PRONTUARIOS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(prontuario.formatarParaArquivo());
        }
    }

    /**
     * Lista todos os prontuários do arquivo
     */
    public String listarProntuarios() throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(ARQUIVO_PRONTUARIOS), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (FileNotFoundException e) {
            return "Nenhum prontuário encontrado.";
        }
        return conteudo.toString();
    }

    /**
     * Valida se todos os campos do prontuário estão preenchidos
     */
    public boolean validarProntuario(Prontuario prontuario) {
        return prontuario.getNomePaciente() != null && !prontuario.getNomePaciente().trim().isEmpty() &&
               prontuario.getCpfPaciente() != null && !prontuario.getCpfPaciente().trim().isEmpty() &&
               prontuario.getNomeMedico() != null && !prontuario.getNomeMedico().trim().isEmpty() &&
               prontuario.getEspecialidade() != null && !prontuario.getEspecialidade().trim().isEmpty() &&
               prontuario.getDataConsulta() != null && !prontuario.getDataConsulta().trim().isEmpty() &&
               prontuario.getDescricaoDiagnostico() != null && !prontuario.getDescricaoDiagnostico().trim().isEmpty() &&
               prontuario.getPrescricaoMedicamentos() != null && !prontuario.getPrescricaoMedicamentos().trim().isEmpty();
    }
} 