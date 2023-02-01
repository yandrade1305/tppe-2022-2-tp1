package br.com.simulador.irpf;

import br.com.simulador.irpf.constants.ImpostoConstantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimuladorIRPF {
    private float valorTotalRendimentos;
    private float valorTotalDeducoes;
    private List<Rendimento> rendimentos = new ArrayList<Rendimento>();
    private List<Deducao> deducoes = new ArrayList<Deducao>();
    private List<Dependente> dependentes = new ArrayList<Dependente>();

    public void cadastrarUmRendimento(String descricao, float valor) {
        Rendimento rendimento = new Rendimento(descricao, valor);
        this.rendimentos.add(rendimento);
        this.valorTotalRendimentos += valor;
    }

    public float getTotalRendimentos() {
        return valorTotalRendimentos;
    }

    public void cadastrarUmaDeducao(String descricao, float valor) {
        Deducao deducao = new Deducao(descricao, valor);
        this.deducoes.add(deducao);
        this.valorTotalDeducoes += deducao.getValor();
    }

    public float getTotalDeducoes() {
        return valorTotalDeducoes;
    }

    public void cadastrarPrevidenciaOficial(String descricao, float valor) {
        PrevidenciaOficial previdenciaOficial = new PrevidenciaOficial(descricao, valor);
        this.deducoes.add(previdenciaOficial);
        this.valorTotalDeducoes += previdenciaOficial.getValor();
    }

    public void cadastrarPensaoAlimenticia(float valor) {
        PensaoAlimenticia pensaoAlimenticia = new PensaoAlimenticia(valor);
        this.deducoes.add(pensaoAlimenticia);
        this.valorTotalDeducoes += pensaoAlimenticia.getValor();
    }

    public float getImposto() {
        return new Imposto(this).computar();
    }


    public float calcularAliquotaEfetiva() {
        float impostoCalculado = getImposto();
        float aliquotaEfetiva = (float) (Math.floor(impostoCalculado / this.getTotalRendimentos() * 10000) / 100.0f);
        return aliquotaEfetiva;
    }

    public float getBaseDeCalculo() {
        float retornoTotal = this.valorTotalRendimentos - this.valorTotalDeducoes;
        return retornoTotal;
    }

    public void cadastrarDependente(String nome, LocalDate dataNascimento) {
        Dependente dependente = new Dependente(nome, dataNascimento);
        dependentes.add(dependente);
        this.valorTotalDeducoes += ImpostoConstantes.VALOR_IMPOSTO_DEPENDENTE;
    }

    public int getNumeroDependentes() {
        return dependentes.size();
    }
}
