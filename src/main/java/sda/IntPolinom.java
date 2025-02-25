package sda;

import java.util.Map;
import logic.DateDeIntrareInvalideException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntPolinom extends Polinom<Integer> {
    @Override
    public void addCoef(Integer grad, Integer coef) {
        this.coefMap.merge(grad, coef, Integer::sum); //suma coeficientilor, daca exista deja o valoare
    }

    @Override
    public String toString() {
        String s = "";
        for(Map.Entry<Integer, Integer> e: this.coefMap.entrySet()) {
            int grad = e.getKey(), coef = e.getValue();
            if (coef != 0) {
                if (coef > 0) {
                    if (!s.isEmpty())
                        s += "+";
                } else
                    s += "-";
                if ((coef != -1 && coef != 1) || grad == 0)
                    s += Math.abs(coef);
                if (grad != 0) {
                    if (grad == 1)
                        s += "x";
                    else
                        s += "x^" + grad;
                }
            }
        }

        return s;
    }

    @Override
    public int grad() {
        for(Map.Entry<Integer, Integer> e: this.coefMap.entrySet()){
            if (e.getValue() != 0)
                return e.getKey();
        }
        return 0;
    }

    public FloatPolinom convertToFloatPolinom(){
        FloatPolinom rez = new FloatPolinom();

        for(Map.Entry<Integer, Integer> e: this.coefMap.entrySet()){
            rez.addCoef(e.getKey(), (float)e.getValue());
        }

        return rez;
    }

    public static IntPolinom convertStringToIntPolinom(String pol) throws DateDeIntrareInvalideException {
        IntPolinom p = new IntPolinom();

        Pattern pattern = Pattern.compile("([+-])?(\\d+)?\\*?([xX])?(\\^(\\d+))?");
        Matcher matcher = pattern.matcher((pol));

        while(matcher.find()) {
            int coef, grad;

            if (matcher.group(1) != null || matcher.group(2) != null || matcher.group(3) != null || matcher.group(4) != null || matcher.group(5) != null) {
                if (matcher.group(1) == null || matcher.group(1).equals("+")) {
                    if (matcher.group(2) == null)
                        coef = 1;
                    else
                        coef = Integer.parseInt(matcher.group(2));
                } else if (matcher.group(1).equals("-")) {
                    if (matcher.group(2) == null)
                        coef = -1;
                    else
                        coef = -Integer.parseInt(matcher.group(2));
                } else throw new DateDeIntrareInvalideException();

                if (matcher.group(5) != null)
                    grad = Integer.parseInt(matcher.group(5));
                else if (matcher.group(3) == null && matcher.group(2) != null)
                    grad = 0;
                else if (matcher.group(3) != null && (matcher.group(3).equals("x") || matcher.group(3).equals("X")))
                    grad = 1;
                else throw new DateDeIntrareInvalideException();

                p.addCoef(grad, coef);
            }
        }

        return p;
    }
}
