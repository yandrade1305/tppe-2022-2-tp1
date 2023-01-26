package br.com.simulador.irpf;

import br.com.simulador.irpf.exception.DescricaoEmBrancoException;
import br.com.simulador.irpf.exception.ValorRendimentoInvalidoException;

public class Rendimento {
    String descricao;
     float valor;

    public Rendimento(String descricao, float valor) {
        if (descricao != null && descricao.isBlank())
            throw new DescricaoEmBrancoException("Não foi possível cadastrar um rendimento com descrição em branco");
        else
            this.descricao = descricao;

        if (valor < 0)
            throw new ValorRendimentoInvalidoException("Não foi possível cadastrar um rendimento com valor inválido");
        else
            this.valor = valor;
    }
}
