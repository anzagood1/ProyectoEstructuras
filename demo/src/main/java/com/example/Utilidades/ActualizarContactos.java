package com.example.Utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.example.Users.Contacto;
import com.example.Users.Empresa;
import com.example.Users.Persona;
import com.example.Users.Usuario;

//Escribe nuevamente toda la lista de contactos en el archivo csv
public class ActualizarContactos {
    public static void actualizarCSV(Usuario usuario){
        borrarCSV(usuario);
        Iterator<Contacto> iterator = usuario.getListaContactos().iterator();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usuario.getNombreArchivo(), true))) {
            bw.write("tipo,id,nombre,telefonos,direcciones,redes_sociales,fechas_de_interes,apellido");
            bw.newLine();
            while (iterator.hasNext()) {
                bw.write(transformar(iterator.next()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    //Borra todos los datos del archivo csv
    public static void borrarCSV(Usuario usuario) {
        String rutaArchivo = usuario.getNombreArchivo();
        try (FileWriter fw = new FileWriter(rutaArchivo, false)) {
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Convierte un mapa a un string JSON simple
    public static String mapToJson(Map<String, String> map) {
        if (map == null || map.isEmpty()) return "{}";
        String json = "{";
        boolean first = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!first) json += ",";
            json += "\"" + entry.getKey().replace("\"", "\"\"") + 
                   "\":\"" + entry.getValue().replace("\"", "\"\"") + "\"";
            first = false;
        }
        json += "}";
        return json;
    }

    //Añade las comillas dobles para completar el string JSON
    public static String escapar(String valor) {
        if (valor == null) return "";
        if (valor.contains(",") || valor.contains("\"")) {
            valor = valor.replace("\"", "\"\"");
            return "\"" + valor + "\"";
        }
        return valor;
    }

    //Retorna el String de cada Contacto que sera escrito en el csv
    public static String transformar(Contacto contacto){
        String texto = null;
        if(contacto instanceof Persona){
            Persona p = (Persona) contacto;
            texto = "P," + contacto.getId() + "," + 
                               escapar(p.getNombre()) + "," +
                               escapar(mapToJson(p.getTelefonos())) + "," +
                               escapar(mapToJson(p.getDirecciones())) + "," +
                               escapar(mapToJson(p.getRedesSociales())) + "," +
                               escapar(mapToJson(p.getFechasDeInteres())) + "," +
                               escapar(p.getApellido());
        }else if(contacto instanceof Empresa){
            texto = "E," + contacto.getId() + "," + 
                               escapar(contacto.getNombre()) + "," +
                               escapar(mapToJson(contacto.getTelefonos())) + "," +
                               escapar(mapToJson(contacto.getDirecciones())) + "," +
                               escapar(mapToJson(contacto.getRedesSociales())) + "," +
                               escapar(mapToJson(contacto.getFechasDeInteres()));
        }
        return texto;
    }

    

}
