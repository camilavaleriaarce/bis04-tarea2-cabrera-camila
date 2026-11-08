# Bitácora · Tarea 2

1. Corregí errores del proyecto de la Semana 8: en `AsociadoArchivoDAO` faltaban los imports, el `throws` de `listar()` y una llave del `try`; en `Asociado` el parámetro de `setSaldoAportes` estaba mal escrito y el saldo no se cambiaba.
2. Completé `AsociadoServicio` con `listar()` y `registrar(Asociado)`, igual que en la sesión.
3. Agregué en `AsociadoServicio` el método `registrarDesdeTexto(String, String, String)`. Recibe el saldo como texto y lo convierte con `Double.parseDouble` dentro de un try-catch. Va en el servicio porque decidir si el saldo es válido es una regla, y las reglas no van en la ventana.
4. Agregué `contarAsociados()` y `calcularTotalAportes()` en `AsociadoServicio`. El `for` que suma los aportes va ahí y no en el botón: si mañana otra ventana o un reporte necesita el total, solo llama al método en vez de escribir la suma otra vez.
5. Agregué `buscarPorCedula(String)` en `AsociadoServicio`. Devuelve el asociado o `null`, y la ventana solo decide qué mensaje mostrar.
6. No hizo falta agregar nada a `AsociadoArchivoDAO`, porque con `listar()` el servicio ya tiene todos los datos que necesita.
7. `VentanaPrincipal` solo recoge lo que se escribe, se lo pasa al servicio y muestra lo que recibe. No importa `File` ni `BufferedReader` y no crea el DAO.
8. Creé la clase `Main` en `presentacion`, que solo abre la ventana. Quité la clase de consola y no queda ningún `System.out.println`.
