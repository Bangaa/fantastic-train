import java.util.Scanner;

public class IanMejias {
    public static String[] parseData(String data) {
        String email, nombre = "", rut = "", tel = "";

        String[] aux = data.split(" ");

        // email
        email = aux[0];


        // rut
        int ri;
        for (ri = 0; ri < aux.length; ri++) {
            if (aux[ri].contains("-")) {
                rut = aux[ri];
                break;
            }
        }

        // nombre
        for (int i = 1; i < ri; i++) {
            nombre += aux[i] + " ";
        }

        // telefono
        for (int i = ri + 1; i < aux.length; i++) {
            tel += aux[i] + " ";
        }

        return new String[]{email, nombre.trim(), rut, tel.trim()};
    }

    public static int obtenerPrecio(String[] listaPrecios, int index) {
        String[] s = listaPrecios[index].split("\\$");
        return Integer.parseInt(s[1]);
    }

    public static void main(String[] args) {
        String[] precioE = {"PV1 $8000", "PV2 $12000", "PV3 $15000",
            "VG $20000", "PVVIP $25000", "VGVIP $35000"
        };

        final int[] stockE = {2, 3, 4, 5, 2, 3};

        int[] ventasE = new int[stockE.length];

        Scanner scn = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // pedir los datos por teclado
            System.out.print("\nIngrese sus datos: ");
            String data = scn.nextLine();

            // separar los datos del string

            String[] persona = parseData(data);

            // encontrar que tipo de entrada debo vender

            int index;

            for (index = 0; index < stockE.length; index++) {
                if (ventasE[index] < stockE[index]) break;
            }

            // obtener el precio de la entrada
            int precio = obtenerPrecio(precioE, index);

            // desplegar los detalles de la transaccion

            System.out.println(String.format(
                        "\nNombre: %s\n" +
                        "RUT: %s\n" +
                        "Telefono: %s\n" +
                        "Monto a cancelar: %d\n" +
                        "Se enviara comprobante al e-mail: %s",

                        persona[1], persona[2], persona[3], precio, persona[0]
                        )
                    );

            ventasE[index] += 1;


            System.out.print("\nContinuar? (S/N): ");
            String ans = scn.nextLine().toLowerCase();

            continuar = ans.equals("s") ? true:false;
        }

        System.out.println("========= RESUMEN DEL DIA ==========");

        // mostrar el monto total de las ventas
        int sumaVenta = 0;

        for (int i = 0; i < precioE.length; i++) {
            sumaVenta += ventasE[i] * obtenerPrecio(precioE, i);
        }

        System.out.println("\nMonto total recaudado: " + sumaVenta);

        // mostrar el stock total disponible

        int stockDisp = 0;

        for (int i = 0; i < precioE.length; i++) {
            stockDisp += stockE[i] - ventasE[i];
        }

        System.out.println("\nStock total disponible: " + stockDisp);
    }
}
