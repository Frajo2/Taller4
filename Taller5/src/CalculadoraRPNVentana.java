import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class CalculadoraRPNVentana extends JFrame {
    private JTextField campoEntrada;
    private JTextArea areaResultado;

    public CalculadoraRPNVentana() {
        setTitle("Calculadora en Notación Polaca Inversa (RPN)");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada y botón
        JPanel panelEntrada = new JPanel(new BorderLayout());

        JLabel etiquetaEntrada = new JLabel("Introduce tu expresión en RPN:");
        campoEntrada = new JTextField();
        JButton botonEvaluar = new JButton("Calcular");

        botonEvaluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = campoEntrada.getText();
                String[] elementos = entrada.split(" ");
                try {
                    int resultado = calcularRPN(elementos);
                    areaResultado.setText("Resultado de la expresión: " + resultado);
                } catch (Exception ex) {
                    areaResultado.setText("Error en la evaluación: " + ex.getMessage());
                }
            }
        });

        // Añadir componentes al panel de entrada
        panelEntrada.add(etiquetaEntrada, BorderLayout.NORTH);
        panelEntrada.add(campoEntrada, BorderLayout.CENTER);
        panelEntrada.add(botonEvaluar, BorderLayout.EAST);

        // Área para mostrar el resultado
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));

        // Área de ejemplos
        JTextArea areaEjemplos = new JTextArea();
        areaEjemplos.setEditable(false);
        areaEjemplos.setText("Ejemplos válidos:\n" +
                "1. 3 4 + 2 * 7 -  (Resultado: 7)\n" +
                "2. 5 1 2 + 4 * + 3 -  (Resultado: 14)\n" +
                "3. 10 2 8 * + 3 -  (Resultado: 23)\n" +
                "4. 4 2 / 3 +  (Resultado: 5)\n" +
                "5. 5 0 /  (Error: División por cero)\n");
        areaEjemplos.setBorder(BorderFactory.createTitledBorder("Ejemplos de Uso"));
        areaEjemplos.setLineWrap(true);
        areaEjemplos.setWrapStyleWord(true);

        // Añadir componentes a la ventana
        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(new JScrollPane(areaEjemplos), BorderLayout.SOUTH);
    }

    public static int calcularRPN(String[] elementos) {
        Stack<Integer> pila = new Stack<>();

        for (String elemento : elementos) {
            if (esNumero(elemento)) {
                pila.push(Integer.parseInt(elemento));
            } else {
                int valor2 = pila.pop();
                int valor1 = pila.pop();
                int resultado = ejecutarOperacion(valor1, valor2, elemento);
                pila.push(resultado);
            }
        }

        return pila.pop();
    }

    private static boolean esNumero(String elemento) {
        try {
            Integer.parseInt(elemento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int ejecutarOperacion(int valor1, int valor2, String operador) {
        switch (operador) {
            case "+":
                return valor1 + valor2;
            case "-":
                return valor1 - valor2;
            case "*":
                return valor1 * valor2;
            case "/":
                if (valor2 == 0) {
                    throw new ArithmeticException("Error: No se puede dividir entre cero.");
                }
                return valor1 / valor2;
            default:
                throw new UnsupportedOperationException("Operador no permitido: " + operador);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraRPNVentana calculadora = new CalculadoraRPNVentana();
            calculadora.setVisible(true);
        });
    }
}
