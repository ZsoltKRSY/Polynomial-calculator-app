package sda;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class Polinom<T extends Number> {
    protected Map<Integer, T> coefMap;

    public Polinom(){
        coefMap = new TreeMap<>(Collections.reverseOrder());
    }

    public T getCoef(Integer grad){
        return coefMap.get(grad);
    }

    public void setCoef(Integer grad, T coef){
        coefMap.put(grad, coef);
    }

    public Set<Map.Entry<Integer, T>> getEntrySet(){
        return coefMap.entrySet();
    }

    public void copyPolinom(Polinom<T> p){coefMap.putAll(p.coefMap);}

    public abstract void addCoef(Integer grad, T coef);

    public abstract int grad();

    public abstract String toString();
}