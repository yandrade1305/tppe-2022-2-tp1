package br.com.simulador.irpf;

import br.com.simulador.irpf.exception.NomeEmBrancoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class CadastroDependenteTest {
    SimuladorIRPF simulador;

    Object[][] dependentes;
    int resultadoEsperado;

    @Before
    public void setup() {
        simulador = new SimuladorIRPF();
    }

    public CadastroDependenteTest(Object[][] dependentes, int resultadoEsperado){
        this.dependentes = dependentes;
        this.resultadoEsperado = resultadoEsperado;
    }

    @Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] parametros = new Object[][]{
                {new Object[][] {
                        {"Jose", LocalDate.of(2000, 11, 14)},
                },1},
                {new Object[][] {
                        {"Jose", LocalDate.of(2000, 11, 14)},
                        {"Maria", LocalDate.of(2002, 8, 21)}
                },2},
                {new Object[][] {
                        {"Jose", LocalDate.of(2000, 11, 14)},
                        {"Maria", LocalDate.of(2002, 8, 21)},
                        {"Dividendos", LocalDate.of(1980, 2, 18)}
                },3},
        };

        return Arrays.asList(parametros);
    }

    @Test
    public void deveCadastrarDependentes(){
        for(Object[] x : dependentes){
            simulador.cadastrarDependente((String)x[0], (LocalDate) x[1]);
        }
        assertEquals(resultadoEsperado, simulador.getNumeroDependentes());
    }

    @Test
    public void naoDeveCadastrarUmDependenteComNomeEmBranco(){
        try {
            simulador.cadastrarDependente("", LocalDate.of(1980, 2, 18));
        } catch (NomeEmBrancoException ex){
            assertEquals("Não foi possível cadastrar um dependente com nome em branco", ex.getMessage());
        }
    }
}
