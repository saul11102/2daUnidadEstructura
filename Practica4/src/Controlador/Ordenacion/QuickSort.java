/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Ordenacion;

import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;

/**
 *
 * @author alejandro
 */
public class QuickSort {
    public static ListaEnlazada<Integer> quickSort(ListaEnlazada<Integer> lista, int tipo) throws Exception {
        if (lista.size() <= 1) {
            return lista;
        }

        int pivotIndex = lista.size() / 2;
        int pivotValue = lista.get(pivotIndex);
        ListaEnlazada<Integer> lesser = new ListaEnlazada<>();
        ListaEnlazada<Integer> greater = new ListaEnlazada<>();

        for (int i = 0; i < lista.size(); i++) {
            if (i == pivotIndex) {
                continue;
            }
            int value = lista.get(i);
            if (tipo == 0 && value <= pivotValue) {
                lesser.insertarNodo(value);
            } else if (tipo == 1 && value >= pivotValue) {
                lesser.insertarNodo(value);
            } else {
                greater.insertarNodo(value);
            }
        }

        lesser = quickSort(lesser, tipo);
        greater = quickSort(greater, tipo);

        ListaEnlazada<Integer> sortedList = new ListaEnlazada<>();
        for (int i = 0; i < lesser.size(); i++) {
            sortedList.insertarNodo(lesser.get(i));
        }
        sortedList.insertarNodo(pivotValue);
        for (int i = 0; i < greater.size(); i++) {
            sortedList.insertarNodo(greater.get(i));
        }

        return sortedList;
    }
}



