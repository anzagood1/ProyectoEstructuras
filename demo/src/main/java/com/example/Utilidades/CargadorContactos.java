package com.example.Utilidades;
import com.example.Users.Contacto;
import com.example.Users.Empresa;
import com.example.Users.Persona;
import com.example.Users.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CargadorContactos {

    //Lee el archivo csv correspondiente y llena la lista de Contactos del usuario
    public static void cargarContactosDesdeCSV(Usuario usuario) {
        String archivo = usuario.getNombreArchivo();
        ObjectMapper objectMapper = new ObjectMapper();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Saltar la línea de encabezado

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // -1 para incluir campos vacíos
                try {
                    char tipo = partes[0].charAt(0);
                    switch (tipo) {
                        case 'P':
                            int idP = Integer.parseInt(partes[1].trim());
                            String nombreP = partes[2].trim().replaceAll("^\"|\"$", "");
                            String telefonosStrP = partes[3].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String direccionesStrP = partes[4].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String redesSocialesStrP = partes[5].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String fechasDeInteresStrP = partes[6].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String apellidoP = partes[7].trim().replaceAll("^\"|\"$", "");
                            Map<String, String> telefonosP = objectMapper.readValue(telefonosStrP, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> direccionesP = objectMapper.readValue(direccionesStrP, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> redesSocialesP = objectMapper.readValue(redesSocialesStrP, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> fechasDeInteresP = objectMapper.readValue(fechasDeInteresStrP, new TypeReference<HashMap<String, String>>() {});
                            Contacto contactoP = new Persona(idP, telefonosP, direccionesP, redesSocialesP, fechasDeInteresP, apellidoP);
                            contactoP.setNombre(nombreP);
                            usuario.getListaContactos().add(contactoP);
                            break;
                        case 'E':
                            int idE = Integer.parseInt(partes[1].trim());
                            String nombreE = partes[2].trim().replaceAll("^\"|\"$", "");
                            String telefonosStrE = partes[3].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String direccionesStrE = partes[4].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String redesSocialesStrE = partes[5].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            String fechasDeInteresStrE = partes[6].trim().replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                            Map<String, String> telefonosE = objectMapper.readValue(telefonosStrE, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> direccionesE = objectMapper.readValue(direccionesStrE, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> redesSocialesE = objectMapper.readValue(redesSocialesStrE, new TypeReference<HashMap<String, String>>() {});
                            Map<String, String> fechasDeInteresE = objectMapper.readValue(fechasDeInteresStrE, new TypeReference<HashMap<String, String>>() {});
                            Contacto contactoE = new Empresa(idE, telefonosE, direccionesE, redesSocialesE, fechasDeInteresE);
                            contactoE.setNombre(nombreE);
                            usuario.getListaContactos().add(contactoE);
                            break;
                        default:
                            System.err.println("Advertencia: Tipo desconocido en la línea: " + linea);
                            continue;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido en la línea: " + linea + " - " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Error al parsear JSON en la línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}