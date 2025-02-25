import logic.DateDeIntrareInvalideException;
import logic.Operatii;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sda.*;

public class OperatiiTest {
    private IntPolinom p = new IntPolinom();
    private IntPolinom q = new IntPolinom();

    @BeforeEach
    public void setup() {
        try {
            p = IntPolinom.convertStringToIntPolinom("5x^4-14x^3+x^2-3x-11");
            q = IntPolinom.convertStringToIntPolinom("x^3-11x+4");
        }
        catch(DateDeIntrareInvalideException ex){}
    }

    @Test
    public void testAdaugare(){
        IntPolinom rez = Operatii.adaugare(p, q);
        Assertions.assertEquals("5x^4-13x^3+x^2-14x-7", rez.toString());
    }

    @Test
    public void testScadere(){
        IntPolinom rez = Operatii.scadere(p, q);
        Assertions.assertEquals("5x^4-15x^3+x^2+8x-15", rez.toString());
    }

    @Test
    public void testInmultire(){
        IntPolinom rez = Operatii.inmultire(p, q);
        Assertions.assertEquals("5x^7-14x^6-54x^5+171x^4-78x^3+37x^2+109x-44", rez.toString());
    }

    @Test
    public void testDiviziune(){
        FloatPolinom rest = new FloatPolinom();
        FloatPolinom rez = Operatii.diviziune(p, q, rest);
        Assertions.assertEquals("5,00x-14,00 + (56,00x^2-177,00x+45,00)/(x^3-11x+4)", rez + " + (" + rest + ")/(" + q + ")");
    }

    @Test
    public void testDerivare(){
        IntPolinom rez = Operatii.derivare(p);
        Assertions.assertEquals("20x^3-42x^2+2x-3", rez.toString());
    }

    @Test
    public void testIntegrare(){
        FloatPolinom rez = Operatii.integrare(p);
        Assertions.assertEquals("x^5-3,50x^4+0,33x^3-1,50x^2-11,00x", rez.toString());
    }
}
