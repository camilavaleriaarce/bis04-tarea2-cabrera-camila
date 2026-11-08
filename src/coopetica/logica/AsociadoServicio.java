package coopetica.logica;

import coopetica.datos.AsociadoArchivoDAO;
import coopetica.modelo.Asociado;
import java.io.IOException;
import java.util.ArrayList;

public class AsociadoServicio {

    private AsociadoArchivoDAO dao;

    public AsociadoServicio() throws IOException {
        this.dao = new AsociadoArchivoDAO();
    }

    public ArrayList<Asociado> listar() throws IOException {
        return dao.listar();
    }

    public String registrar(Asociado a) throws IOException {

        if (a.getCedula().isEmpty() || a.getNombre().isEmpty()) {
            return "La cedula y el nombre son obligatorios.";
        }

        if (a.getSaldoAportes() < 0) {
            return "El saldo no puede ser negativo.";
        }

        for (Asociado asociado : dao.listar()) {
            if (asociado.getCedula().equals(a.getCedula())) {
                return "Ya existe un asociado con esa cedula.";
            }
        }

        dao.agregar(a);
        return "OK";
    }

    public String registrarDesdeTexto(String cedula, String nombre, String saldoTexto) throws IOException {

        if (cedula.trim().isEmpty() || nombre.trim().isEmpty() || saldoTexto.trim().isEmpty()) {
            return "La cedula, el nombre y el saldo son obligatorios.";
        }

        double saldo;
        try {
            saldo = Double.parseDouble(saldoTexto.trim());
        } catch (NumberFormatException e) {
            return "El saldo debe ser un numero.";
        }

        return registrar(new Asociado(cedula.trim(), nombre.trim(), saldo));
    }

    public int contarAsociados() throws IOException {
        int cantidad = 0;
        for (Asociado a : dao.listar()) {
            cantidad = cantidad + 1;
        }
        return cantidad;
    }

    public double calcularTotalAportes() throws IOException {
        double total = 0;
        for (Asociado a : dao.listar()) {
            total = total + a.getSaldoAportes();
        }
        return total;
    }

    public Asociado buscarPorCedula(String cedula) throws IOException {
        for (Asociado a : dao.listar()) {
            if (a.getCedula().equals(cedula.trim())) {
                return a;
            }
        }
        return null;
    }
}
