package br.com.simulador.irpf;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculoAliquotaEfetivaTest {

    SimuladorIRPF simulador;

    Object[][] rendimentos;
    Object[][] dependentes;
    Object[][] pensoesAlimenticias;
    Object[][] previdencias;
    Object[][] deducoes;

    float aliquotaEsperada;

    @Before
    public void setup() {
        simulador = new SimuladorIRPF();
    }

    public CalculoAliquotaEfetivaTest(Object[][] rendimentos,
                                      Object[][] dependentes,
                                      Object[][] pensoesAlimenticias,
                                      Object[][] previdencias,
                                      Object[][] deducoes,
                                      float aliquotaEsperada){
        this.rendimentos = rendimentos;
        this.dependentes = dependentes;
        this.pensoesAlimenticias = pensoesAlimenticias;
        this.previdencias = previdencias;
        this.deducoes = deducoes;
        this.aliquotaEsperada = aliquotaEsperada;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] parametros = new Object[][]{
                {new Object[][]{{"Salario", 5500f}},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        11.69f
                },
                {new Object[][]{{"Salario", 2100F}},
                        new Object[][]{{"Jose", LocalDate.of(2000, 11, 14)}},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        0.02f
                },
                {new Object[][]{{"Salario", 30000F}},
                        new Object[][]{{"Jose", LocalDate.of(2000, 11, 14)}},
                        new Object[][]{{ 1600f }},
                        new Object[][]{{null}},
                        new Object[][]{{null}},
                        22.96f
                },
                {new Object[][]{{"Salario", 30000F}},
                        new Object[][]{{"Jose", LocalDate.of(2000, 11, 14)}},
                        new Object[][]{{ 1600f }},
                        new Object[][]{{"Pensão por morte", 200f}},
                        new Object[][]{{null}},
                        22.77f
                },
                {new Object[][]{{"Salario", 30000F}},
                        new Object[][]{{"Jose", LocalDate.of(2000, 11, 14)}},
                        new Object[][]{{ 1600f }},
                        new Object[][]{{"Pensão por morte", 200f}},
                        new Object[][]{{"Previdencia privada", 8000F}},
                        15.44f
                },
        };

        return Arrays.asList(parametros);

    }

    @Test
    public void deveCalcularAliquota(){
        for(Object[] x : rendimentos){
            if(x[0] != null)
                simulador.cadastrarUmRendimento((String) x[0], (float) x[1]);
        }
        for(Object[] x : dependentes){
            if(x[0] != null)
                simulador.cadastrarDependente((String) x[0], (LocalDate) x[1]);
        }
        for(Object[] x : pensoesAlimenticias){
            if(x[0] != null)
                simulador.cadastrarPensaoAlimenticia((float) x[0]);
        }
        for(Object[] x : previdencias){
            if(x[0] != null)
                simulador.cadastrarPrevidenciaOficial((String) x[0], (float) x[1]);
        }
        for(Object[] x : deducoes){
            if(x[0] != null)
                simulador.cadastrarUmaDeducao((String) x[0], (float) x[1]);
        }
        float aliquotaEfetiva = simulador.calcularAliquotaEfetiva();
        TestCase.assertEquals(aliquotaEsperada, aliquotaEfetiva, 0F);
    }
}
