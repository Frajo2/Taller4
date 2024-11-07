import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class VerificadorParentesisGUI extends JFrame {

    private JTextArea areaEntrada;
    private JLabel etiquetaResultado;

    public VerificadorParentesisGUI() {
        // Configuración de la ventana
        setTitle("Comprobador de Paréntesis Balanceados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Área de texto para la entrada
        areaEntrada = new JTextArea();
        areaEntrada.setLineWrap(true);
        areaEntrada.setWrapStyleWord(true);
        JScrollPane panelDesplazamiento = new JScrollPane(areaEntrada);
        add(panelDesplazamiento, BorderLayout.CENTER);

        // Botón para comprobar
        JButton botonVerificar = new JButton("Comprobar");
        botonVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formula = areaEntrada.getText();
                String resultado = verificarBalance(formula);
                etiquetaResultado.setText("Resultado: " + resultado);
            }
        });
        add(botonVerificar, BorderLayout.SOUTH);

        // Etiqueta para mostrar el resultado
        etiquetaResultado = new JLabel("Resultado: ");
        add(etiquetaResultado, BorderLayout.NORTH);

        // Ejemplos de uso
        JLabel etiquetaEjemplos = new JLabel("<html>Ejemplos de expresiones:<br>" +
                "1. ( { [ ] } )  -> Balanceada<br>" +
                "2. ( { [ ] )    -> No balanceada<br>" +
                "3. [ { ( ) } ]  -> Balanceada<br>" +
                "4. { [ ( ] }    -> No balanceada</html>");
        add(etiquetaEjemplos, BorderLayout.WEST);
    }

    public String verificarBalance(String formula) {
        Stack<Character> stack = new Stack<>();

        for (char simboloActual : formula.toCharArray()) {
            // Si es un paréntesis de apertura, lo agregamos a la pila
            if (simboloActual == '(' || simboloActual == '{' || simboloActual == '[') {
                stack.push(simboloActual);
            }
            // Si es un paréntesis de cierre
            else if (simboloActual == ')' || simboloActual == '}' || simboloActual == ']') {
                // Verificamos si la pila está vacía o si el paréntesis no coincide
                if (stack.isEmpty()) {
                    return "No balanceada";
                }
                char aperturaReciente = stack.pop();
                if (!coincideParentesis(aperturaReciente, simboloActual)) {
                    return "No balanceada";
                }
            }
        }

        // Al final, la pila debe estar vacía si está balanceada
        return stack.isEmpty() ? "Balanceada" : "No balanceada";
    }

    private boolean coincideParentesis(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '{' && cierre == '}') ||
                (apertura == '[' && cierre == ']');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VerificadorParentesisGUI().setVisible(true);
            }
        });
    }
}
