import java.util.Scanner;
import java.util.Stack;

public class VerificadorParentesis {

    public static String verificarBalance(String formula) {
        Stack<Character> stack = new Stack<>();

        for (char simboloActual : formula.toCharArray()) {
            // Si encontramos un paréntesis de apertura, lo agregamos a la pila
            if (simboloActual == '(' || simboloActual == '{' || simboloActual == '[') {
                stack.push(simboloActual);
            }
            // Si encontramos un paréntesis de cierre
            else if (simboloActual == ')' || simboloActual == '}' || simboloActual == ']') {
                // Revisamos si la pila está vacía o si el paréntesis no coincide
                if (stack.isEmpty()) {
                    return "No balanceada";
                }
                char aperturaReciente = stack.pop();
                if (!coincideParentesis(aperturaReciente, simboloActual)) {
                    return "No balanceada";
                }
            }
        }

        // Al final, la pila debe estar vacía para que esté balanceada
        return stack.isEmpty() ? "Balanceada" : "No balanceada";
    }

    private static boolean coincideParentesis(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '{' && cierre == '}') ||
                (apertura == '[' && cierre == ']');
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Mensaje de bienvenida y ejemplos para el usuario
        System.out.println("Ingrese una expresión matemática para comprobar si está balanceada.");
        System.out.println("Ejemplos de expresiones:");
        System.out.println("1. ( { [ ] } )  -> Balanceada");
        System.out.println("2. ( { [ ] )    -> No balanceada");
        System.out.println("3. [ { ( ) } ]  -> Balanceada");
        System.out.println("4. { [ ( ] }    -> No balanceada");

        System.out.print("Escribe tu expresión: ");
        String formula = entrada.nextLine();  // Leer la entrada del usuario

        // Comprobamos si la expresión está balanceada
        String resultado = verificarBalance(formula);
        System.out.println("Resultado: " + resultado);

        entrada.close();  // Cerrar el scanner
    }
}
