/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;

import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.Direccion;
import Modelo.Estudiante;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
public class EstudianteDAO extends AdaptadorDAO<Estudiante> {

    private Estudiante estudiante;

    public EstudianteDAO() {
        super(Estudiante.class);
    }

    public Estudiante getEstudiante() {
        if (this.estudiante == null) {
            this.estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void guardar() throws IOException {
        estudiante.setId(generarId());
        this.guardar(estudiante);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(estudiante, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Estudiante> ordenarPorCedula() throws Exception {
        ListaEnlazada<Estudiante> estudiantes = listar();
        int n = estudiantes.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (estudiantes.get(j).getCedula().compareToIgnoreCase(estudiantes.get(minIndex).getCedula()) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Estudiante temp = estudiantes.get(i);
                estudiantes.modificar(estudiantes.get(minIndex), i);
                estudiantes.modificar(temp, minIndex);
            }
        }

        return estudiantes;
    }

    public ListaEnlazada<Estudiante> ordenarPorNombre() throws Exception {
        ListaEnlazada<Estudiante> estudiantes = listar();
        int n = estudiantes.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (estudiantes.get(j).getNombre().compareToIgnoreCase(estudiantes.get(minIndex).getNombre()) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Estudiante temp = estudiantes.get(i);
                estudiantes.modificar(estudiantes.get(minIndex), i);
                estudiantes.modificar(temp, minIndex);
            }
        }

        return estudiantes;
    }

    public ListaEnlazada<Estudiante> buscarEstudianteBinario(String clave, String tipoBusqueda) throws Exception {
        ListaEnlazada<Estudiante> estudiantes = obtenerListaOrdenada(tipoBusqueda);
        ListaEnlazada<Estudiante> estudiantesEncontrados = new ListaEnlazada<>();

        int izquierda = 0;
        int derecha = estudiantes.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Estudiante estudianteActual = estudiantes.get(medio);
            String claveEstudiante = getClaveEstudiante(estudianteActual, tipoBusqueda);

            if (claveEstudiante.toLowerCase().contains(clave.toLowerCase())) {
                estudiantesEncontrados.insertarNodo(estudianteActual);
                break;
            }

            if (claveEstudiante.toLowerCase().compareTo(clave.toLowerCase()) >= 0) {
                derecha = medio - 1;
            } else {
                izquierda = medio + 1;
            }
        }

        return estudiantesEncontrados;
    }

    private ListaEnlazada<Estudiante> obtenerListaOrdenada(String tipoBusqueda) throws Exception {
        switch (tipoBusqueda.toLowerCase()) {
            case "cedula":
                return ordenarPorCedula();
            case "nombre":
                return ordenarPorNombre();
            case "ciudad":
                return ordenarPorCiudad();
            default:
                throw new IllegalArgumentException("Tipo de búsqueda inválido: " + tipoBusqueda);
        }
    }

    public ListaEnlazada<Estudiante> buscarEstudianteLinealBinario(String clave, String tipoBusqueda) throws Exception {
        ListaEnlazada<Estudiante> estudiantes = obtenerListaOrdenada(tipoBusqueda);
        ListaEnlazada<Estudiante> estudiantesEncontrados = new ListaEnlazada<>();

        if (tipoBusqueda.equalsIgnoreCase("ciudad")) {
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudianteActual = estudiantes.get(i);
                String claveEstudiante = getClaveEstudiante(estudianteActual, tipoBusqueda);

                if (claveEstudiante.toLowerCase().contains(clave.toLowerCase())) {
                    estudiantesEncontrados.insertarNodo(estudianteActual);
                }
            }
        } else {
            int izquierda = 0;
            int derecha = estudiantes.size() - 1;
            boolean encontrado = false;

            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Estudiante estudianteActual = estudiantes.get(medio);
                String claveEstudiante = getClaveEstudiante(estudianteActual, tipoBusqueda);

                if (claveEstudiante.toLowerCase().contains(clave.toLowerCase())) {
                    encontrado = true;
                    estudiantesEncontrados.insertarNodo(estudianteActual);

                    int indiceIzquierda = medio - 1;
                    while (indiceIzquierda >= 0) {
                        Estudiante estudianteIzquierda = estudiantes.get(indiceIzquierda);
                        String claveEstudianteIzquierda = getClaveEstudiante(estudianteIzquierda, tipoBusqueda);
                        if (claveEstudianteIzquierda.toLowerCase().contains(clave.toLowerCase())) {
                            estudiantesEncontrados.insertarNodo(estudianteIzquierda);
                            indiceIzquierda--;
                        } else {
                            break;
                        }
                    }

                    int indiceDerecha = medio + 1;
                    while (indiceDerecha < estudiantes.size()) {
                        Estudiante estudianteDerecha = estudiantes.get(indiceDerecha);
                        String claveEstudianteDerecha = getClaveEstudiante(estudianteDerecha, tipoBusqueda);
                        if (claveEstudianteDerecha.toLowerCase().contains(clave.toLowerCase())) {
                            estudiantesEncontrados.insertarNodo(estudianteDerecha);
                            indiceDerecha++;
                        } else {
                            break;
                        }
                    }

                    break; 
                } else if (claveEstudiante.toLowerCase().compareTo(clave.toLowerCase()) < 0) {
                    izquierda = medio + 1;
                } else {
                    derecha = medio - 1;
                }
            }

            if (!encontrado) {
                estudiantesEncontrados = new ListaEnlazada<>(); 
            }
        }

        return estudiantesEncontrados;
    }
    
    private String getClaveEstudiante(Estudiante estudiante, String tipoBusqueda) throws Exception {
        DireccionDAO d = new DireccionDAO();
        if (tipoBusqueda.equalsIgnoreCase("cedula")) {
            return estudiante.getCedula();
        } else if (tipoBusqueda.equalsIgnoreCase("nombre")) {
            return estudiante.getNombre();
        } else if (tipoBusqueda.equalsIgnoreCase("ciudad")) {
            Direccion direccion = d.buscarPorId(estudiante.getId_Direccion());
            return direccion.getCiudad();
        }
        return "";
    }

    private ListaEnlazada<Estudiante> ordenarPorCiudad() throws Exception {
        ListaEnlazada<Estudiante> estudiantes = listar();
        ListaEnlazada<Direccion> direcciones = new DireccionDAO().ordenarPorCiudad();
        ListaEnlazada<Estudiante> resultado = new ListaEnlazada<>();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            for (int j = 0; j < direcciones.size(); j++) {
                Direccion d = direcciones.get(j);
                if (d.getId().intValue() == e.getId_Direccion().intValue()) {
                    resultado.insertarNodo(e);
                }
            }

        }
        return resultado;
    }
}
