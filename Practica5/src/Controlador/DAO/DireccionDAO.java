/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;

import Controlador.ed.lista.ListaEnlazada;
import Modelo.Direccion;
import Modelo.Estudiante;
import java.io.IOException;

/**
 *
 * @author alejandro
 */
public class DireccionDAO extends AdaptadorDAO<Direccion> {

    private Direccion direccion;

    public DireccionDAO() {
        super(Direccion.class);
    }

    public Direccion getDireccion() {
        if (this.direccion == null) {
            this.direccion = new Direccion();
        }
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void guardar() throws IOException {
        direccion.setId(generarId());
        this.guardar(direccion);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(direccion, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Direccion> ordenarPorCiudad() throws Exception {
        ListaEnlazada<Direccion> direcciones = listar();
        int n = direcciones.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                String ciudadActual = direcciones.get(j).getCiudad();
                String ciudadMin = direcciones.get(minIndex).getCiudad();

                if (ciudadActual.compareToIgnoreCase(ciudadMin) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Direccion temp = direcciones.get(minIndex);
                direcciones.modificar(direcciones.get(i), minIndex);
                direcciones.modificar(temp, i);
            }
        }

        return direcciones;
    }

    public ListaEnlazada<Direccion> buscarPorCiudad(String ciudad) throws Exception {
        ListaEnlazada<Direccion> direccionesOrdenadas = ordenarPorCiudad();
        ListaEnlazada<Direccion> direccionesEncontradas = new ListaEnlazada<>();

        for (int i = 0; i < direccionesOrdenadas.size(); i++) {
            Direccion direccion = direccionesOrdenadas.get(i);
            if (direccion.getCiudad().equalsIgnoreCase(ciudad)) {
                direccionesEncontradas.insertarNodo(direccion);
            }
        }

        return direccionesEncontradas;
    }

    public ListaEnlazada<Direccion> buscarPorCiudadBinaria(String ciudad) throws Exception {
        ListaEnlazada<Direccion> direccionesOrdenadas = ordenarPorCiudad();
        int izquierda = 0;
        int derecha = direccionesOrdenadas.size() - 1;
        ListaEnlazada<Direccion> direccionesEncontradas = new ListaEnlazada<>();

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Direccion direccionActual = direccionesOrdenadas.get(medio);

            if (direccionActual.getCiudad().equalsIgnoreCase(ciudad)) {
                direccionesEncontradas.insertarNodo(direccionActual);
                break;
            }

            if (direccionActual.getCiudad().compareToIgnoreCase(ciudad) < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return direccionesEncontradas;
    }

    public Direccion buscarPorId(int id) throws Exception {
        ListaEnlazada<Direccion> direcciones = listar();
        for (int i = 0; i < direcciones.size(); i++) {
            Direccion direccion = direcciones.get(i);
            if (direccion.getId() == id) {
                return direccion;
            }
        }
        throw new Exception("Dirección no encontrada con el ID: " + id);
    }

}
