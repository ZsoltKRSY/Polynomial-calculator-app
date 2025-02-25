package logic;

import sda.FloatPolinom;
import sda.IntPolinom;

import java.util.Map;

public class Operatii {
    public static IntPolinom adaugare(IntPolinom p, IntPolinom q){
        IntPolinom rez = new IntPolinom();
        rez.copyPolinom(p);

        for(Map.Entry<Integer, Integer> e: q.getEntrySet())
            rez.addCoef(e.getKey(), e.getValue()); //suma coeficientilor

        return rez;
    }

    public static IntPolinom scadere(IntPolinom p, IntPolinom q){
        IntPolinom rez = new IntPolinom();
        rez.copyPolinom(p);

        for(Map.Entry<Integer, Integer> e: q.getEntrySet())
            rez.addCoef(e.getKey(), -e.getValue()); //diferenta coeficientilor

        return rez;
    }

    public static IntPolinom inmultire(IntPolinom p, IntPolinom q){
        IntPolinom rez = new IntPolinom();

        for(Map.Entry<Integer, Integer> e1: p.getEntrySet()){
            for(Map.Entry<Integer, Integer> e2: q.getEntrySet()){
                rez.addCoef(e1.getKey() + e2.getKey(), e1.getValue() * e2.getValue());
            }
        }

        return rez;
    }

    public static FloatPolinom diviziune(IntPolinom p, IntPolinom q, FloatPolinom rest){
        FloatPolinom rez = new FloatPolinom();
        FloatPolinom pfloat = p.convertToFloatPolinom();
        for(int i = 0; i <= pfloat.grad(); ++i)
            pfloat.addCoef(i, 0.0F);

        int qgrad = q.grad();

        for(Map.Entry<Integer, Float> e: pfloat.getEntrySet()){
            int pgrad = pfloat.grad();

            if (pgrad < qgrad)
                break;

            int gradx = e.getKey() - qgrad;
            float coefx = e.getValue() / q.getCoef(qgrad);

            FloatPolinom div = new FloatPolinom();
            for(Map.Entry<Integer, Integer> f: q.getEntrySet()){
                div.addCoef(f.getKey() + gradx, f.getValue() * coefx);
            }

            for(Map.Entry<Integer, Float> f: div.getEntrySet()){
                pfloat.addCoef(f.getKey(), -f.getValue());
            }
            if (pfloat.getCoef(pgrad) != 0.0F)
                pfloat.setCoef(pgrad, 0.0F);

            rez.addCoef(gradx, coefx);
        }

        rest.copyPolinom(pfloat);
        return rez;
    }

    public static IntPolinom derivare(IntPolinom p){
        IntPolinom rez = new IntPolinom();

        for(Map.Entry<Integer, Integer> e: p.getEntrySet()){
            if (e.getKey() > 0)
                rez.addCoef(e.getKey() - 1, e.getValue() * e.getKey());
        }

        return rez;
    }

    public static FloatPolinom integrare(IntPolinom p){
        FloatPolinom rez = new FloatPolinom();

        for(Map.Entry<Integer, Integer> e: p.getEntrySet())
            rez.addCoef(e.getKey() + 1, (float)e.getValue() / (e.getKey() + 1));

        return rez;
    }
}
