import java.util.Scanner;
import java.util.Stack;

public class CalculadoraRPN {

    public static int calcularRPN(String[] elementos) {
        Stack<Integer> pila = new Stack<>();

        for (String elemento : elementos) {
            if (esNumero(elemento)) {
                // Si el elemento es un número, lo colocamos en la pila
                pila.push(Integer.parseInt(elemento));
            } else {
                // Si es un operador, sacamos dos valores de la pila
                int valor2 = pila.pop();
                int valor1 = pila.pop();
                int resultado = realizarOperacion(valor1, valor2, elemento);
                // Colocamos el resultado en la pila
                pila.push(resultado);
            }
        }

        // Al final, la pila debe contener el resultado único de la operación
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

    private static int realizarOperacion(int valor1, int valor2, String operador) {
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
        Scanner entrada = new Scanner(System.in);

        System.out.println("***************************************");
        System.out.println(" Calculadora de Expresiones RPN ");
        System.out.println("***************************************");
        System.out.println("Escribe una expresión en notación RPN:");
        System.out.println("Ejemplos válidos:");
        System.out.println("1. 3 4 + 2 * 7 -  (Resultado: 7)");
        System.out.println("2. 5 1 2 + 4 * + 3 -  (Resultado: 14)");
        System.out.println("3. 10 2 8 * + 3 -  (Resultado: 23)");
        System.out.println("4. 4 2 / 3 +  (Resultado: 5)");
        System.out.println("5. 5 0 /  (Error: División por cero)");
        System.out.println("***************************************");
        System.out.print("Introduce tu expresión: ");

        String entradaUsuario = entrada.nextLine();
        String[] elementos = entradaUsuario.split(" ");

        try {
            int resultado = calcularRPN(elementos);
            System.out.println("***************************************");
            System.out.println("El resultado de la expresión es: " + resultado);
            System.out.println("***************************************");
        } catch (Exception e) {
            System.out.println("Error al calcular la expresión: " + e.getMessage());
        } finally {
            entrada.close();
        }
    }
}
