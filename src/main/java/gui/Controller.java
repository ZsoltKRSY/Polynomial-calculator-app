package gui;

import logic.DateDeIntrareInvalideException;
import logic.Operatii;
import sda.FloatPolinom;
import sda.IntPolinom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    public Controller(View view){
        this.view = view;

        view.addAdaugareListener(new AdaugareListener());
        view.addScadereListener(new ScadereListener());
        view.addInmultireListener(new InmultireListener());
        view.addDiviziuneListener(new DiviziuneListener());
        view.addDerivareListener(new DerivareListener());
        view.addIntegrareListener(new IntegrareListener());
    }

    class AdaugareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            IntPolinom p = view.getTextField1Input();
            IntPolinom q = view.getTextField2Input();

            if (p != null && q != null) {
                IntPolinom rez = Operatii.adaugare(p, q);
                view.setRezultatTextField(rez.toString());
            }
        }
    }

    class ScadereListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            IntPolinom p = view.getTextField1Input();
            IntPolinom q = view.getTextField2Input();

            if (p != null && q != null) {
                IntPolinom rez = Operatii.scadere(p, q);
                view.setRezultatTextField(rez.toString());
            }
        }
    }

    class InmultireListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            IntPolinom p = view.getTextField1Input();
            IntPolinom q = view.getTextField2Input();

            if (p != null && q != null) {
                IntPolinom rez = Operatii.inmultire(p, q);
                view.setRezultatTextField(rez.toString());
            }
        }
    }

    class DiviziuneListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            IntPolinom p = view.getTextField1Input(), q = view.getTextField2Input();

            if (p != null && q != null) {
                try {
                    if (p.grad() < q.grad())
                        throw new DateDeIntrareInvalideException();

                    FloatPolinom rest = new FloatPolinom();
                    FloatPolinom rez = Operatii.diviziune(p, q, rest);
                    if (rest.toString().isEmpty())
                        view.setRezultatTextField(rez.toString());
                    else
                        view.setRezultatTextField(rez + " + (" + rest + ")/(" + q + ")");
                }
                catch(DateDeIntrareInvalideException ex){
                    view.showMessage("Gradul celui de al doilea polinom trebuie sa fie mai mic sau egal cu cel al primului!");
                }
            }
        }
    }

    class DerivareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            IntPolinom p = view.getTextField1Input();

            if (p != null) {
                IntPolinom rez = Operatii.derivare(p);
                view.setRezultatTextField(rez.toString());
            }
        }
    }

    class IntegrareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            IntPolinom p = view.getTextField1Input();

            if (p != null) {
                FloatPolinom rez = Operatii.integrare(p);
                view.setRezultatTextField(rez.toString());
            }
        }
    }
}
