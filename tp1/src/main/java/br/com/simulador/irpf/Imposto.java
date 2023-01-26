package br.com.simulador.irpf;

public class Imposto {

        private float getTotalImpostos(float baseDeCalculo, double VALOR_LIMITE_FAIXA, double valorDaFaixa,
                        float totalImpostos, double porcentagemFaixa) {
                if (baseDeCalculo > VALOR_LIMITE_FAIXA) {
                        totalImpostos += valorDaFaixa * porcentagemFaixa;
                }
                return totalImpostos;
        }

        public float getImposto(float baseDeCalculo) {

                double VALOR_LIMITE_FAIXA1 = 1903.98;
                double VALOR_LIMITE_FAIXA2 = 922.67;
                double VALOR_LIMITE_FAIXA3 = 924.40;
                double VALOR_LIMITE_FAIXA4 = 913.63;
                double VALOR_LIMITE_FAIXA5 = VALOR_LIMITE_FAIXA4 + VALOR_LIMITE_FAIXA3 + VALOR_LIMITE_FAIXA2
                                + VALOR_LIMITE_FAIXA1;

                double porcentagemFaixa2 = 0.075;
                double porcentagemFaixa3 = 0.15;
                double porcentagemFaixa4 = 0.225;
                double porcentagemFaixa5 = 0.275;

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
}