package sda;

import java.util.Map;

public class FloatPolinom extends Polinom<Float> {
    @Override
    public void addCoef(Integer grad, Float coef) {
        this.coefMap.merge(grad, coef, Float::sum); //Float::sum - in loc de metoda Lambda; suma coeficientilor, daca exista deja o valoare
    }

    @Override
    public String toString() {
        String s = "";
        for(Map.Entry<Integer, Float> e: this.coefMap.entrySet()) {
            int grad = e.getKey();
            float coef = e.getValue();
            if (coef != 0) {
                if (coef > 0) {
                    if (!s.isEmpty())
                        s += "+";
                } else
                    s += "-";
                if ((coef != -1 && coef != 1) || grad == 0)
                    s += String.format("%.2f", Math.abs(coef));
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
        for(Map.Entry<Integer, Float> e: this.coefMap.entrySet()){
            if (e.getValue() != 0)
                return e.getKey();
        }
        return 0;
    }
}
