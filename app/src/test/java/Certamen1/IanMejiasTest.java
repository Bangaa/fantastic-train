package Certamen1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class IanMejiasTest {
    @Test void aunHayEntradas() {
        int[] stock = {3,1,4,5};
        int[] ventas = {3,1,3,0};

        assertTrue(IanMejias.quedanEntradas(stock, ventas));
    }

    @Test void noQuedanEntradas() {
        int[] stock = {3,1,4,5};
        int[] ventas = {3,1,4,5};

        assertFalse(IanMejias.quedanEntradas(stock, ventas));
    }

    @Test void parseData() {
        String data = "ejemplo@gmail.com Ian Mejias Conejeros 12345678-9 +56987654321";
        String[] expected = new String[] {
            "ejemplo@gmail.com", 
            "Ian Mejias Conejeros",
            "12345678-9",
            "+56987654321"
        };

        String[] actual = IanMejias.parseData(data);

        assertArrayEquals(expected, actual); 
    }

    @Test void parseDataWithExtraSpaces() {
        String data = "ejemplo@gmail.com       Ian Mejias       Conejeros 12345678-9   +56987654321";
        String[] expected = new String[] {
            "ejemplo@gmail.com", 
            "Ian Mejias       Conejeros",
            "12345678-9",
            "+56987654321"
        }; 

        String[] actual = IanMejias.parseData(data);

        assertArrayEquals(expected, actual); 
    }

    @Test void obtenerPrecio() {
        String[] precioE = {"PV1 $8000", "PV2 $12000", "PV3 $15000",
            "VG $20000", "PVVIP $25000", "VGVIP $35000"
        };

        int precio = IanMejias.obtenerPrecio(precioE, 0);
        assertEquals(8000, precio);

        precio = IanMejias.obtenerPrecio(precioE, 5);
        assertEquals(35000, precio);
    }
}
