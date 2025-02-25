package gui;

import sda.*;
import logic.DateDeIntrareInvalideException;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel mainPanel;
    private JLabel pol1Label;
    private JLabel pol2Label;
    private JLabel polRezLabel;
    private JButton adaugareButton;
    private JButton scadereButton;
    private JButton inmultireButton;
    private JButton diviziuneButton;
    private JButton derivareButton;
    private JButton integrareButton;
    private JTextField pol1TextField;
    private JTextField pol2TextField;
    private JTextField polRezTextField;

    public View(){
        this.initGui();
    }

    public void initGui(){
        this.setTitle("Calculator pentru polinoame");
        this.setSize(750, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(this.mainPanel);

        this.initMainPanel();
        this.setVisible(true);
    }

    public void initMainPanel(){
        polRezTextField.setEditable(false);
        pol1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        pol2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        polRezLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setRezultatTextField(String str){
        polRezTextField.setText(str);
    }

    public IntPolinom getTextField1Input() {
        try {
            IntPolinom p = IntPolinom.convertStringToIntPolinom(pol1TextField.getText());
            if (p.toString().isEmpty())
                throw new DateDeIntrareInvalideException();
            return p;
        }
        catch(DateDeIntrareInvalideException e){
            this.showMessage("Date de intrare invalide!");
        }

        return null;
    }

    public IntPolinom getTextField2Input() {
        try {
            IntPolinom p = IntPolinom.convertStringToIntPolinom(pol2TextField.getText());
            if (p.toString().isEmpty())
                throw new DateDeIntrareInvalideException();
            return p;
        }
        catch(DateDeIntrareInvalideException e){
            this.showMessage("Date de intrare invalide!");
        }

        return null;
    }

    public void addAdaugareListener(ActionListener al) {
        adaugareButton.addActionListener(al);
    }

    public void addScadereListener(ActionListener al) {
        scadereButton.addActionListener(al);
    }

    public void addInmultireListener(ActionListener al) {
        inmultireButton.addActionListener(al);
    }

    public void addDiviziuneListener(ActionListener al) {
        diviziuneButton.addActionListener(al);
    }

    public void addDerivareListener(ActionListener al) {
        derivareButton.addActionListener(al);
    }

    public void addIntegrareListener(ActionListener al) {
        integrareButton.addActionListener(al);
    }
}
