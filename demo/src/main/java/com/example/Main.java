package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

import com.example.Comparadores.ComparadorEdad;
import com.example.Comparadores.ComparadorNatural;
import com.example.Comparadores.ComparadorPorNombre;
import com.example.Comparadores.ComparadorTipo;
import com.example.Listas.ListaEnlazadaDobleCircular;
import com.example.Listas.MiArrayList;
import com.example.Listas.Nodo;
import com.example.Users.Contacto;
import com.example.Users.Usuario;
import com.example.Utilidades.ActualizarContactos;
import com.example.Utilidades.CargadorContactos;
import com.example.Utilidades.EscritorDeContactos;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static MiArrayList<Usuario> listaUsuarios = new MiArrayList<Usuario>(Usuario.class);

    //Metodo para llenar la lista de Usuarios usando un archivo txt
    public static void llenarUsuarios(){
        MiArrayList<String> lineas = new MiArrayList<String>(String.class);
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("demo\\usuarios.txt");
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (int i = 0; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split(",");
            Usuario u = new Usuario(datos[0], datos[1], datos[2]);
            listaUsuarios.add(u);
        }
    }

    //Metodo para navegar la lista de Contactos
    public static void navegacion(Usuario usuario){
        ListaEnlazadaDobleCircular<Contacto> lista = usuario.getListaContactos();
                    Nodo<Contacto> nodo = lista.getHead();
                    nodo.dato.mostrarContacto();
                    System.out.println("");
                    int opcionZ = 0;
                    while(opcionZ !=3){
                        System.out.println("1.- Siguiente contacto");
                        System.out.println("2.- Anterior contacto");
                        System.out.println("3.- Regresar al menu");
                        System.out.println("");
                        System.out.print("Elija una opcion: ");
                        opcionZ = scanner.nextInt();
                        System.out.println(""); 
                        switch (opcionZ) {
                            case 1:
                                System.out.println("Ciclando al contacto siguiente...");
                                System.out.println("");
                                nodo = nodo.next;
                                nodo.dato.mostrarContacto();
                                System.out.println("");
                                break;
                            case 2:
                                System.out.println("Ciclando al contacto anterior...");
                                System.out.println("");
                                nodo = nodo.previous;
                                nodo.dato.mostrarContacto();
                                System.out.println("");
                                break;
                            case 3:
                                System.out.println("Regresando al menu principal...");
                                System.out.println("");
                                break;
                        
                            default:
                                System.out.println("La opcion elegida no se encuentra en el menu...");
                                System.out.println("");
                                break;
                        }
                    }
    }

    //Metodo para añadir un Contacto a la lista y actualizar el archivo CSV
    public static void añadirContacto(Usuario usuario){
        usuario.getListaContactos().add(EscritorDeContactos.escribirContactos(usuario));
        ActualizarContactos.actualizarCSV(usuario);
        System.out.println("");
    }

    //Metodo para ordenar la lista de Contactos de varias maneras
    public static void ordenar(Usuario usuario){
        int opcionY = 0;
                    do{
                        System.out.println("1.- Ordenar por nombre");
                        System.out.println("2.- Ordenar por cumpleaños");
                        System.out.println("3.- Ordenar por tipo de contacto ");
                        System.out.println("");
                        System.out.print("Elija una opcion: ");
                        opcionY = scanner.nextInt();
                        System.out.println("");
                        switch (opcionY) {
                            case 1:
                                System.out.println("Ordenando por nombre...");
                                MiArrayList<Contacto> listaTempNombre = new MiArrayList<Contacto>(Contacto.class);
                                Iterator<Contacto> iterableNom = usuario.getListaContactos().iterator();
                                while (iterableNom.hasNext()) {
                                    listaTempNombre.add(iterableNom.next());
                                }
                                listaTempNombre.sort(new ComparadorPorNombre());
                                ListaEnlazadaDobleCircular<Contacto> enlazadaTempNom = new ListaEnlazadaDobleCircular<>();
                                for (int i = 0; i < listaTempNombre.size(); i++) {
                                    enlazadaTempNom.add(listaTempNombre.get(i));
                                }
                                usuario.setListaContactos(enlazadaTempNom);
                                ActualizarContactos.actualizarCSV(usuario);
                                System.out.println("");
                                break;
                            case 2:
                                System.out.println("Ordenando por cumpleaños...");
                                MiArrayList<Contacto> listaTempCump = new MiArrayList<Contacto>(Contacto.class);
                                Iterator<Contacto> iterableCump = usuario.getListaContactos().iterator();
                                while (iterableCump.hasNext()) {
                                    listaTempCump.add(iterableCump.next());
                                }
                                listaTempCump.sort(new ComparadorEdad());
                                ListaEnlazadaDobleCircular<Contacto> enlazadaTempCump = new ListaEnlazadaDobleCircular<>();
                                for (int i = 0; i < listaTempCump.size(); i++) {
                                    enlazadaTempCump.add(listaTempCump.get(i));
                                }
                                usuario.setListaContactos(enlazadaTempCump);
                                ActualizarContactos.actualizarCSV(usuario);
                                System.out.println("");
                                
                                break;
                            case 3:
                                System.out.println("Ordenando por tipo...");
                                MiArrayList<Contacto> listaTempTipo = new MiArrayList<Contacto>(Contacto.class);
                                Iterator<Contacto> iterableTipo = usuario.getListaContactos().iterator();
                                while (iterableTipo.hasNext()) {
                                    listaTempTipo.add(iterableTipo.next());
                                }
                                listaTempTipo.sort(new ComparadorTipo());
                                ListaEnlazadaDobleCircular<Contacto> enlazadaTempTipo = new ListaEnlazadaDobleCircular<>();
                                for (int i = 0; i < listaTempTipo.size(); i++) {
                                    enlazadaTempTipo.add(listaTempTipo.get(i));
                                }
                                usuario.setListaContactos(enlazadaTempTipo);
                                ActualizarContactos.actualizarCSV(usuario);
                                System.out.println("");
                                
                                break;
                        
                            default:
                                System.out.println("La opcion elegida no se encuentra en el menu...");
                                System.out.println("");
                                break;
                        }
                    }while(opcionY < 0 || opcionY > 3);
    }

    //Metodo para salir del programa
    public static void salir(Usuario usuario){
        System.out.println("Saliendo del programa...");
                    MiArrayList<Contacto> listaTemp = new MiArrayList<Contacto>(Contacto.class);
                    Iterator<Contacto> iterator = usuario.getListaContactos().iterator();
                    while (iterator.hasNext()) {
                        listaTemp.add(iterator.next());
                    }
                    listaTemp.sort(new ComparadorNatural());
                    ListaEnlazadaDobleCircular<Contacto> enlazadaTemp = new ListaEnlazadaDobleCircular<>();
                    for (int i = 0; i < listaTemp.size(); i++) {
                        enlazadaTemp.add(listaTemp.get(i));
                    }
                    usuario.setListaContactos(enlazadaTemp);
                    ActualizarContactos.actualizarCSV(usuario);
    }

    //Metodo para acceder a las utilidades de la aplicacion
    public static void menu(Usuario usuario){
        CargadorContactos.cargarContactosDesdeCSV(usuario);
        System.out.println("Bienvenido " + usuario.getUsuario() + "!");
        System.out.println("");
        int opcionX = 0;
        while(opcionX !=5){
            System.out.println("1.- Desplegar la lista de contactos");
            System.out.println("2.- Navegar la lista de contactos");
            System.out.println("3.- Añadir contacto");
            System.out.println("4.- Reordenar lista de contactos");
            System.out.println("5.- Salir");
            System.out.println("");
            System.out.print("Elija una opcion: ");
            opcionX = scanner.nextInt();
            System.out.println("");
            switch (opcionX) {
                case 1:
                    usuario.desplegarContactos();
                    break;
                case 2:
                    navegacion(usuario);
                    break;
                case 3:
                    añadirContacto(usuario);
                    break;
                case 4:
                    ordenar(usuario);
                    break;
                case 5:
                    salir(usuario);
                    break;
            
                default:
                    System.out.println("La opcion elegida no se encuentra en el menu...");
                    System.out.println("");
                    break;
            }
        };
    }

    public static void main(String[] args) {
        llenarUsuarios();
        Boolean correcto = true;
        String username = null;
        String contrasena = null;
        Usuario usuario = null;
        while(correcto){
            System.out.println("Ingrese su usuario: ");
            username = scanner.nextLine();
            System.out.println("Ingrese su contraseña");
            contrasena = scanner.nextLine();
            System.out.println("");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if(listaUsuarios.get(i).getContrasena().equals(contrasena) && listaUsuarios.get(i).getUsuario().equalsIgnoreCase(username)){
                    usuario = listaUsuarios.get(i);
                    correcto = false;
                }
            }
            if(usuario == null){
                System.out.println("Usuario o contraseña incorrectos...");
                System.out.println("");
            }
        }
        menu(usuario);   
    }
}

