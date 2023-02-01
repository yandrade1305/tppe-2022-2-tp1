package br.com.simulador.irpf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ImpostoTest {
    SimuladorIRPF simulador;
    
    Object[][] impostos;
    double resultadoImposto;

    @Before
    public void setup() {
        simulador = new SimuladorIRPF();
    }
    
    public ImpostoTest(Object[][] impostos, double resultadoImposto){
    	this.impostos = impostos;
    	this.resultadoImposto = resultadoImposto;
    }
    
    @Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] parametros = new Object[][]{
                {new Object[][] {
                        {"Salário", 2600f},
                },52.20f},
                {new Object[][] {
                        {"Salário", 2000f},
                        {"Renda Fixa", 200f},
                        {"Liquidação de ações", 400f}
                },52.20F},
                {new Object[][] {
                    {"Salário", 3000f},
                    {"Renda Fixa", 200f},
                    {"Liquidação de ações", 250f}
                },162.70F},
                {new Object[][] {
                    {"Salário", 4000f},
                    {"Renda Fixa", 350f},
                    {"Liquidação de ações", 50f}
                },353.87},
                {new Object[][] {
                    {"Salário", 4800f},
                    {"Renda Fixa", 800f},
                    {"Liquidação de ações", 400f}
                },780.64F},
        };

        return Arrays.asList(parametros);
    }
    
    @Test
    public void deveCadastrarRendimentos(){
        for(Object[] x : impostos){
            simulador.cadastrarUmRendimento((String)x[0], (float) x[1]);
        }
        assertEquals(resultadoImposto, simulador.getImposto(), 2f);
    }
}
