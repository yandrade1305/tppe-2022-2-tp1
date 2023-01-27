package br.com.simulador.irpf.constants;

public class ImpostoConstantes {
    public static final double VALOR_LIMITE_FAIXA1 = 1903.98;
    public static final double VALOR_LIMITE_FAIXA2 = 922.67;
    public static final double VALOR_LIMITE_FAIXA3 = 924.40;
    public static final double VALOR_LIMITE_FAIXA4 = 913.63;
    public static final double VALOR_LIMITE_FAIXA5 = VALOR_LIMITE_FAIXA4 + VALOR_LIMITE_FAIXA3 + VALOR_LIMITE_FAIXA2
            + VALOR_LIMITE_FAIXA1;

    public static final double PORCENTAGEM_FAIXA2 = 0.075;
    public static final double PORCENTAGEM_FAIXA3 = 0.15;
    public static final double PORCENTAGEM_FAIXA4 = 0.225;
    public static final double PORCENTAGEM_FAIXA5 = 0.275;

    public static final float VALOR_IMPOSTO_DEPENDENTE = 189.59f;
}
