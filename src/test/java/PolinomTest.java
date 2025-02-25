import logic.DateDeIntrareInvalideException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sda.FloatPolinom;
import sda.IntPolinom;

public class PolinomTest {
    IntPolinom p = new IntPolinom();
    FloatPolinom q = new FloatPolinom();

    @BeforeEach
    public void setup(){
        try {
            p = IntPolinom.convertStringToIntPolinom("5x^4-14x^3+x^2-3x-11");
            q = p.convertToFloatPolinom();
        }
        catch(DateDeIntrareInvalideException ex){}
    }

    @Test
    public void testGetCoef(){
        Assertions.assertEquals(-14, p.getCoef(3));
        Assertions.assertEquals(-14.00F, q.getCoef(3));
    }

    @Test
    public void testCopyPolinom(){
        IntPolinom rez1 = new IntPolinom();
        rez1.copyPolinom(p);
        FloatPolinom rez2 = new FloatPolinom();
        rez2.copyPolinom(q);

        Assertions.assertEquals(p.toString(), rez1.toString());
        Assertions.assertEquals(q.toString(), rez2.toString());
    }

    @Test
    public void testAddCoef(){
        p.addCoef(5, -3);
        q.addCoef(5, 1.30F);

        Assertions.assertEquals("-3x^5+5x^4-14x^3+x^2-3x-11", p.toString());
        Assertions.assertEquals("1,30x^5+5,00x^4-14,00x^3+x^2-3,00x-11,00", q.toString());
    }

    @Test
    public void testGrad(){
        Assertions.assertEquals(4, p.grad());
        Assertions.assertEquals(4, q.grad());
    }

    @Test
    public void testConvertToFloatPolinom(){
        q = p.convertToFloatPolinom();

        Assertions.assertEquals("5,00x^4-14,00x^3+x^2-3,00x-11,00", q.toString());
    }

    @Test
    public void testToString(){
        Assertions.assertEquals("5x^4-14x^3+x^2-3x-11", p.toString());
        Assertions.assertEquals("5,00x^4-14,00x^3+x^2-3,00x-11,00", q.toString());
    }

    @Test
    public void testConvertStringToIntPolinom(){
        IntPolinom r = new IntPolinom();
        r.addCoef(4, 5);
        r.addCoef(3, -14);
        r.addCoef(2, 1);
        r.addCoef(1, -3);
        r.addCoef(0, -11);

        Assertions.assertEquals(r.toString(), p.toString());
    }
}
