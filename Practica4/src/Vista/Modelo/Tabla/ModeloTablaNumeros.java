/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Modelo.Tabla;

import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alejandro
 */
    public class ModeloTablaNumeros extends AbstractTableModel {
        private ListaEnlazada<Integer> lista = new ListaEnlazada<>();

        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Integer s = null;
            try {
                s = lista.get(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (i1) {
                case 0:
                    return s;
                default:
                    return null;
            }

        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Numeros";
                default:
                    return null;
            }
        }

        /**
         * @return the lista
         */
        public ListaEnlazada<Integer> getLista() {
            return lista;
        }

        /**
         * @param lista the lista to set
         */
        public void setLista(ListaEnlazada<Integer> lista) {
            this.lista = lista;
        }
    }
