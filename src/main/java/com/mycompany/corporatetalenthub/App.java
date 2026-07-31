package com.mycompany.corporatetalenthub;

import com.mycompany.corporatetalenthub.modelo.Empleado;
import com.mycompany.corporatetalenthub.modelo.EmpresaRecord;

public class App {

    public static void main(String[] args) {
        String encabezado = """
                =====================================
                     CORPORATE TALENT HUB
                   Gestión del talento humano
                =====================================
                """;
        System.out.println(encabezado);
        
        Empleado empleado = new Empleado(
                (byte) 3,             // byte
                (short) 2024,         // short
                102,                  // int: ID par
                1_023_456_789L,       // long: sufijo L
                92.5f,                // float: sufijo f
                3_000_000.0,          // double
                'I',                  // char: contrato indefinido
                true,                 // boolean
                "Laura Gómez",        // String
                27,
                2,
                500_000.0);
        
        EmpresaRecord empresa = new EmpresaRecord(
                "CodeUp Solutions",
                "900123456-7",
                2015);
        
        System.out.println(empleado);
        
        /*  
            empleado.setNombre(null);
            empleado.getNombre().length();
            
            En Java 8
        
            Exception in thread "main" java.lang.NullPointerException
            at Empleado.mostrarNombre(Empleado.java:12)
        
            NullPointerException ocurrió en la línea 12
            Pero no decía qué variable era null.
        
            ----------------------------------------------------------
        
            En Java 14+
            
            Exception in thread "main" java.lang.NullPointerException:
            Cannot invoke "String.length()" because "this.nombre" is null}
        
            Exception in thread "main" java.lang.NullPointerException: 
            Cannot invoke "String.length()" because the return value of 
            "com.mycompany.corporatetalenthub.modelo.Empleado.getNombre()" is null
            at com.mycompany.corporatetalenthub.App.main(App.java:55)

        */
        
        // Se crean dos objetos distintos con el mismo contenido.
        String texto1 = new String("Hola");
        String texto2 = new String("Hola");

        // Comparación con ==
        // El resultado será false porque el operador == compara las referencias,
        // es decir, verifica si ambas variables apuntan al mismo espacio de memoria
        // en el Heap, no si tienen el mismo contenido.
        //
        // Aunque texto1 y texto2 contienen "Hola", cada uno fue creado como un
        // objeto diferente, por lo que ocupan posiciones distintas en la memoria Heap.
        System.out.println(texto1 == texto2); // false
        
        
    }
}
