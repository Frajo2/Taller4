import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PilaGUI {
    private JPanel pGeneral;
    private JTextArea txtResultado;
    private JTextField txtElemento;
    private JButton desapilarButton;
    private JButton apilarButton;
    private JButton invertirButton;

    public Pila p=new Pila();

    public PilaGUI() {
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.desapilar(txtResultado);
            }
        });
        apilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elemento=txtElemento.getText();
                try{
                    int valor=Integer.parseInt(elemento);
                    p.apilar(valor,txtResultado);
                    txtElemento.setText("");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Elemento no valido");
                }
            }
        });
        invertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.invertirPila(txtResultado);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PilaGUI");
        frame.setContentPane(new PilaGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
