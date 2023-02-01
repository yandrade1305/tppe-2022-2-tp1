package br.com.simulador.irpf;

import static br.com.simulador.irpf.constants.ImpostoConstantes.*;


public class Imposto {

        float totalImpostos;

        double VALOR_LIMITE_FAIXA321;
        double VALOR_LIMITE_FAIXA21;
        float baseDeCalculo;

        public Imposto(SimuladorIRPF simuladorIRPF) {
                baseDeCalculo = simuladorIRPF.getBaseDeCalculo();
        }

        private float getTotalImpostos(float baseDeCalculo, double VALOR_LIMITE_FAIXA, double valorDaFaixa,
                        float totalImpostos, double porcentagemFaixa) {
                if (baseDeCalculo > VALOR_LIMITE_FAIXA) {
                        totalImpostos += valorDaFaixa * porcentagemFaixa;
                }
                return totalImpostos;
        }

        public float computar() {

                totalImpostos = getTotalImpostos(baseDeCalculo,
                                VALOR_LIMITE_FAIXA5,
                                baseDeCalculo - VALOR_LIMITE_FAIXA5,
                                totalImpostos,
                                PORCENTAGEM_FAIXA5);

                VALOR_LIMITE_FAIXA321 = VALOR_LIMITE_FAIXA3 + VALOR_LIMITE_FAIXA2 + VALOR_LIMITE_FAIXA1;

                totalImpostos = getTotalImpostos(baseDeCalculo,
                                VALOR_LIMITE_FAIXA321,
                                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA321, VALOR_LIMITE_FAIXA4),
                                totalImpostos,
                                PORCENTAGEM_FAIXA4);

                VALOR_LIMITE_FAIXA21 = VALOR_LIMITE_FAIXA2 + VALOR_LIMITE_FAIXA1;
                totalImpostos = getTotalImpostos(baseDeCalculo,
                                VALOR_LIMITE_FAIXA21,
                                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA21, VALOR_LIMITE_FAIXA3),
                                totalImpostos,
                                PORCENTAGEM_FAIXA3);

                totalImpostos = getTotalImpostos(baseDeCalculo,
                                VALOR_LIMITE_FAIXA1,
                                Math.min(baseDeCalculo - VALOR_LIMITE_FAIXA1, VALOR_LIMITE_FAIXA2),
                                totalImpostos,
                                PORCENTAGEM_FAIXA2);

                return totalImpostos;
        }
}