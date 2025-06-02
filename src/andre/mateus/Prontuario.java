package andre.mateus;

/**
 * Classe que representa um prontuário médico
 */
public class Prontuario {
    private String nomePaciente;
    private String cpfPaciente;
    private String nomeMedico;
    private String especialidade;
    private String dataConsulta;
    private String descricaoDiagnostico;
    private String prescricaoMedicamentos;

    /**
     * Construtor da classe Prontuario
     */
    public Prontuario(String nomePaciente, String cpfPaciente, String nomeMedico,
                     String especialidade, String dataConsulta, String descricaoDiagnostico,
                     String prescricaoMedicamentos) {
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.descricaoDiagnostico = descricaoDiagnostico;
        this.prescricaoMedicamentos = prescricaoMedicamentos;
    }

    // Getters e Setters
    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDescricaoDiagnostico() {
        return descricaoDiagnostico;
    }

    public void setDescricaoDiagnostico(String descricaoDiagnostico) {
        this.descricaoDiagnostico = descricaoDiagnostico;
    }

    public String getPrescricaoMedicamentos() {
        return prescricaoMedicamentos;
    }

    public void setPrescricaoMedicamentos(String prescricaoMedicamentos) {
        this.prescricaoMedicamentos = prescricaoMedicamentos;
    }

    /**
     * Retorna o prontuário formatado para salvar no arquivo
     */
    public String formatarParaArquivo() {
        StringBuilder sb = new StringBuilder();
        sb.append("==========\n");
        sb.append("Nome do Paciente: ").append(nomePaciente).append("\n");
        sb.append("CPF: ").append(cpfPaciente).append("\n");
        sb.append("Nome do Médico: ").append(nomeMedico).append("\n");
        sb.append("Especialidade: ").append(especialidade).append("\n");
        sb.append("Data da Consulta: ").append(dataConsulta).append("\n");
        sb.append("Descrição do Diagnóstico: ").append(descricaoDiagnostico).append("\n");
        sb.append("Prescrição de Medicamentos: ").append(prescricaoMedicamentos).append("\n");
        sb.append("==========\n");
        return sb.toString();
    }
} 