/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivosbinarios;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author josue
 */
public class EmpleadoMain {

    public static void main(String[] args) {
        EmpleadoManager empleadoManager = new EmpleadoManager();
        Scanner lea = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(" **** MENU ****");
            System.out.println("1 - Agregar Empleado");
            System.out.println("2 - Listar Empleados No Despedidos");
            System.out.println("3 - Agregar Venta al Empleado");
            System.out.println("4 - Pagar Empleado");
            System.out.println("5 - Despedir a Empleado");
            System.out.println("6 - Salir");
            System.out.print("Escoja una opción: ");

            opcion = lea.nextInt();
            lea.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del empleado: ");
                    String name = lea.nextLine();
                    System.out.print("Ingrese el salario del empleado: ");
                    double salary = lea.nextDouble();
                    try {
                        empleadoManager.addEmployee(name, salary);
                        System.out.println("Empleado agregado exitosamente.");
                    } catch (IOException e) {
                        System.out.println("Error al agregar empleado: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        empleadoManager.employeeList();
                    } catch (IOException e) {
                        System.out.println("Error al listar empleados: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el codigo del empleado: ");
                    int code = lea.nextInt();
                    System.out.print("Ingrese el monto de la venta: ");
                    double saleAmount = lea.nextDouble();
                    try {
                        empleadoManager.addSaleToEmployee(code, saleAmount);
                    } catch (IOException e) {
                        System.out.println("Error al agregar venta: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el codigo del empleado a pagar: ");
                    int employeeCodeToPay = lea.nextInt();
                    try {
                        empleadoManager.payEmployee(employeeCodeToPay);
                    } catch (IOException e) {
                        System.out.println("Error al pagar al empleado: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el codigo del empleado a despedir: ");
                    int employeeCodeToFire = lea.nextInt();
                    try {
                        boolean result = empleadoManager.fireEmployee(employeeCodeToFire);
                        if (result) {
                            System.out.println("Empleado despedido exitosamente.");
                        } else {
                            System.out.println("No se encontro al empleado o ya esta despedido.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error al despedir empleado: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema");
                    break;

                default:
                    System.out.println("Opcion no valida, intente nuevamente.");
            }

        } while (opcion != 6);
        lea.close();
    }
}
