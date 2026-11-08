/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.datos;

import coopetica.modelo.Asociado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AsociadoArchivoDAO {

    private String nombreArchivo = "datos/asociados.txt";

    public AsociadoArchivoDAO() throws IOException {
        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    public ArrayList<Asociado> listar() throws IOException {

        ArrayList<Asociado> lista = new ArrayList<>();

        try (BufferedReader bR = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = bR.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    lista.add(new Asociado(datos[0], datos[1],
                                           Double.parseDouble(datos[2])));
                }
            }
        }
        return lista;
    }

    public void agregar(Asociado a) throws IOException {

        try (BufferedWriter bW = new BufferedWriter(
                 new FileWriter(nombreArchivo, true))) {
            bW.append(a.getCedula() + "," + a.getNombre() + "," + a.getSaldoAportes());
            bW.newLine();
        }
    }
}
