/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios;

/**
 *
 * @author josue
 */
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileTest {

    Scanner lea = new Scanner(System.in);
    int opcion = 0;
    MyFile mf = new MyFile();

    public void Correr() {
        do {

            System.out.println("1- Set Archivo/Folder");
            System.out.println("2- Ver Informacion");
            System.out.println("3- Crear Archivo");
            System.out.println("4- Crear un folder");
            System.out.println("5- Borrar Files");
            System.out.println("6- DIR");
            System.out.println("7- TREE");
            System.out.println("8- reescribir texto");
            System.out.println("9- Agregar texto");
            System.out.println("10- Leer Contenido Archivo (reader)");
            System.out.println("11- Leer Contendio Archivo (buffer)");
            System.out.println("12- Salir");
            System.out.print("Escoja su opcion: ");
            try {
                opcion = lea.nextInt();
                lea.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la direccion de su archivo: ");
                        String direccion = lea.nextLine();
                        mf.setFile(direccion);
                        System.out.println("Archivo/folder establecido.");
                        break;
                    case 2:
                        mf.info();
                        break;
                    case 3:
                        try {
                            if (mf.crearFile()) {
                                System.out.println("Archivo creado exitosamente.");
                            } else {
                                System.out.println("El archivo ya existe o no se pudo crear.");
                            }
                        } catch (IOException e) {
                            System.out.println("Error al crear el archivo: " + e.getMessage());
                        }
                        break;
                    case 4:
                        if (mf.crearFolder()) {
                            System.out.println("Carpeta creada exitosamente.");
                        } else {
                            System.out.println("La carpeta ya existe o no se pudo crear.");
                        }
                        break;
                    case 5:
                        mf.borrar();
                        break;
                    case 6:
                        mf.dir();
                        break;
                    case 7:
                        mf.tree();
                        break;
                    case 8:
                        System.out.println("Ingrese el texto que desea reescribir en el archivo:");
                        String textoReescribir = lea.nextLine();
                        mf.reescribir(textoReescribir);
                        break;

                    case 9:
                        System.out.println("Ingrese el texto que desea añadir al final del archivo:");
                        String textoAñadir = lea.nextLine();
                        mf.addinfo(textoAñadir);
                        break;
                    case 10:
                        mf.leerArchivoReader();
                        break;
                    case 11:
                        mf.leerArchivobuffer();
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 12.");
                lea.nextLine();

            } catch (NullPointerException e) {
                System.out.println("Error: Un valor nulo fue encontrado. Asegúrese de que los parámetros no sean nulos.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (opcion != 12);
    }

    public static void main(String[] args) {
        FileTest fileTest = new FileTest();
        fileTest.Correr();
    }
}
