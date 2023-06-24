/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Modelo.Tabla;

import Controlador.ed.lista.ListaEnlazada;
import Modelo.Estudiante;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alejandro
 */
public class ModeloTablaEstudiante extends AbstractTableModel{
    private ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Estudiante s = null;
        try {
            s = lista.get(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (i1) {
            case 0:
                return (s != null) ? s.getNombre() : "No definido";
            case 1:
                return (s != null) ? s.getCedula() : "No definido";
            case 2:
                return (s != null) ? s.getDireccion().getCalle() : "No definido";
            case 3:
                return (s != null) ? s.getDireccion().getCiudad() : "No definido";
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Cédula";
            case 2:
                return "Calle";
            case 3:
                return "Ciudad";
            default:
                return null;
        }
    }

    /**
     * @return the lista
     */
    public ListaEnlazada<Estudiante> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Estudiante> lista) {
        this.lista = lista;
    }

}
