/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Ordenacion;

import Controlador.ed.lista.ListaEnlazada;
import Controlador.ed.lista.NodoLista;

/**
 *
 * @author alejandro
 */
public class MergeSort {
    public static ListaEnlazada<Integer> mergeSort(ListaEnlazada<Integer> lista, int tipo) throws Exception {
        if (lista.size() <= 1) {
            return lista;
        }

        int medio = lista.size() / 2;
        ListaEnlazada<Integer> mitadIzquierda = new ListaEnlazada<>();
        ListaEnlazada<Integer> mitadDerecha = new ListaEnlazada<>();

        for (int i = 0; i < medio; i++) {
            mitadIzquierda.insertarNodo(lista.get(i));
        }

        for (int i = medio; i < lista.size(); i++) {
            mitadDerecha.insertarNodo(lista.get(i));
        }

        mitadIzquierda = mergeSort(mitadIzquierda, tipo);
        mitadDerecha = mergeSort(mitadDerecha, tipo);

        return merge(mitadIzquierda, mitadDerecha, tipo);
    }

    private static ListaEnlazada<Integer> merge(ListaEnlazada<Integer> listaIzquierda, ListaEnlazada<Integer> listaDerecha, int tipo) throws Exception {
        ListaEnlazada<Integer> listaOrdenada = new ListaEnlazada<>();

        while (!listaIzquierda.isEmpty() && !listaDerecha.isEmpty()) {
            if (tipo == 0) { // Ascendente
                if (listaIzquierda.get(0) <= listaDerecha.get(0)) {
                    listaOrdenada.insertarNodo(listaIzquierda.get(0));
                    listaIzquierda.delete(0);
                } else {
                    listaOrdenada.insertarNodo(listaDerecha.get(0));
                    listaDerecha.delete(0);
                }
            } else { // Descendente
                if (listaIzquierda.get(0) >= listaDerecha.get(0)) {
                    listaOrdenada.insertarNodo(listaIzquierda.get(0));
                    listaIzquierda.delete(0);
                } else {
                    listaOrdenada.insertarNodo(listaDerecha.get(0));
                    listaDerecha.delete(0);
                }
            }
        }

        while (!listaIzquierda.isEmpty()) {
            listaOrdenada.insertarNodo(listaIzquierda.get(0));
            listaIzquierda.delete(0);
        }

        while (!listaDerecha.isEmpty()) {
            listaOrdenada.insertarNodo(listaDerecha.get(0));
            listaDerecha.delete(0);
        }

        return listaOrdenada;
    }
}