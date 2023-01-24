package br.com.simulador.irpf;

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

    public float calcularAliquotaEfetiva() {
        float imposto = getImposto();
        float aliquotaEfetiva = (float)(Math.floor(imposto/this.getTotalRendimentos() * 10000) / 100.0f);
        return aliquotaEfetiva;
    }

    public float getBaseDeCalculo() {
        float retornoTotal = this.valorTotalRendimentos - this.valorTotalDeducoes;
        return retornoTotal;
    }

    public float getImposto() {

        double VALOR_LIMITE_FAIXA1 = 1903.98;
        double VALOR_LIMITE_FAIXA2 = 922.67;
        double VALOR_LIMITE_FAIXA3 = 924.40;
        double VALOR_LIMITE_FAIXA4 = 913.63;
        double VALOR_LIMITE_FAIXA5 = VALOR_LIMITE_FAIXA4 + VALOR_LIMITE_FAIXA3 + VALOR_LIMITE_FAIXA2 + VALOR_LIMITE_FAIXA1;

        double porcentagemFaixa2 = 0.075;
        double porcentagemFaixa3 = 0.15;
        double porcentagemFaixa4 = 0.225;
        double porcentagemFaixa5 = 0.275;

        float baseDeCalculo = getBaseDeCalculo();
        float totalImpostos = 0f;

        totalImpostos = getTotalImpostos(baseDeCalculo,
                VALOR_LIMITE_FAIXA5,
                baseDeCalculo - VALOR_LIMITE_FAIXA5,
                totalImpostos,
                porcentagemFaixa5);

        double VALOR_LIMITE_FAIXA321 = VALOR_LIMITE_FAIXA3 + VALOR_LIMITE_FAIXA2 + VALOR_LIMITE_FAIXA1;

        totalImpostos = getTotalImpostos(baseDeCalculo,
                VALOR_LIMITE_FAIXA321,
                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA321, VALOR_LIMITE_FAIXA4),
                totalImpostos,
                porcentagemFaixa4);

        double VALOR_LIMITE_FAIXA21 = VALOR_LIMITE_FAIXA2 + VALOR_LIMITE_FAIXA1;
        
        totalImpostos = getTotalImpostos(baseDeCalculo,
                VALOR_LIMITE_FAIXA21,
                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA21, VALOR_LIMITE_FAIXA3),
                totalImpostos,
                porcentagemFaixa3);

        totalImpostos = getTotalImpostos(baseDeCalculo,
                VALOR_LIMITE_FAIXA1,
                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA1, VALOR_LIMITE_FAIXA2),
                totalImpostos,
                porcentagemFaixa2);

        return totalImpostos;
    }

    private float getTotalImpostos(float baseDeCalculo, double VALOR_LIMITE_FAIXA, double valorDaFaixa, float totalImpostos, double porcentagemFaixa) {
        if (baseDeCalculo > VALOR_LIMITE_FAIXA) {
            totalImpostos += valorDaFaixa * porcentagemFaixa;
        }
        return totalImpostos;
    }

    public void cadastrarDependente(String nome, LocalDate dataNascimento) {
        Dependente dependente = new Dependente(nome, dataNascimento);
        dependentes.add(dependente);
        this.valorTotalDeducoes += 189.59f;
    }

    public int getNumeroDependentes() {
        return dependentes.size();
    }
}
