package br.com.simulador.irpf;

import br.com.simulador.irpf.constants.ImpostoConstantes;
import br.com.simulador.irpf.exception.NomeEmBrancoException;

import java.time.LocalDate;

public class Dependente extends Deducao {
    private String nome;
    private LocalDate dataNascimento;

    public Dependente(String nome, LocalDate dataNascimento) {
        super("Dependente", ImpostoConstantes.VALOR_IMPOSTO_DEPENDENTE);
        if(nome != null && nome.isBlank())
            throw new NomeEmBrancoException("Não foi possível cadastrar um dependente com nome em branco");
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
