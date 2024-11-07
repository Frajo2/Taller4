import javax.swing.*;

public class Pila {
    public Nodo cima;
    public int tamano;

    public Pila(){
        cima=null;
        tamano=0;
    }
    public boolean estaVacia(){
        return cima == null;
    }
    public void apilar(int dato, JTextArea textArea){
        Nodo nuevoNodo=new Nodo(dato);
        nuevoNodo.siguiente=cima;
        cima=nuevoNodo;
        tamano++;
        actualizarTextArea(textArea);
    }
    public int desapilar(JTextArea textArea){
        if(estaVacia()){
            JOptionPane.showMessageDialog(null,"La pila esta Vacia");
            return -1;
        }
        int dato=cima.dato;
        cima = cima.siguiente;
        tamano--;

        if (textArea != null){
            actualizarTextArea(textArea);
        }
        return dato;
    }
    public void mostrarPila(JTextArea textArea){
        if (estaVacia()){
            textArea.setText("La Pila esta Vacia");
        }else {
            StringBuilder pilaStr=new StringBuilder();
            Nodo actual=cima;
            while (actual!=null){
                pilaStr.append(actual.dato).append("\n");
                actual=actual.siguiente;
            }
            textArea.setText(pilaStr.toString());
        }
    }
    public void actualizarTextArea(JTextArea textArea){
        mostrarPila(textArea);
    }
    public void invertirPila(JTextArea textArea) {
        if (estaVacia()) {
            textArea.setText("La Pila está vacía");
            return;
        }

        StringBuilder pilaInvertida = new StringBuilder();
        while (!estaVacia()) {
            pilaInvertida.append(desapilar(textArea)).append("\n");
        }
        for (String datoStr : pilaInvertida.toString().split("\n")) {
            if (!datoStr.isEmpty()) {
                apilar(Integer.parseInt(datoStr), textArea);
            }
        }
        textArea.setText(pilaInvertida.toString());
    }

}
