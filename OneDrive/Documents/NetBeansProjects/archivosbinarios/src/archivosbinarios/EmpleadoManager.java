/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivosbinarios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author josue
 */
public class EmpleadoManager {

    private RandomAccessFile rcods, remps;

    public EmpleadoManager() {
        try {
            //1-Asegurar que el folder compnay exista 
            File mf = new File("company");
            mf.mkdir();
            //2- Instanciar RAFs dentro de company
            rcods = new RandomAccessFile("company/codigos.emp", "rw");
            remps = new RandomAccessFile("company/empleados.emp", "rw");
            //3-inicializar el archivo de codigos si es nuevo.
            initCodes();
        } catch (IOException e) {

        }
    }

    private void initCodes() throws IOException {
        if (rcods.length() == 0) //Puntero->
        {
            rcods.writeInt(1);
        }
        //Puntero->

    }

    private int getCode() throws IOException {
        rcods.seek(0);
        //Puntero
        int code = rcods.readInt();
        //puntero
        rcods.seek(0);
        rcods.writeInt(code + 1);
        return code;
    }

    public void addEmployee(String name, double salary) throws IOException {
        //Asegurar que el puntero este en el final del archivo.
        remps.seek(remps.length());
        int code = getCode();
        //P-> 0 36
        remps.writeInt(code);
        //P-> 4 40
        remps.writeUTF(name); //Ana 8
        //P-> 12
        remps.writeDouble(salary);
        //P-> 20
        remps.writeLong(Calendar.getInstance().getTimeInMillis());
        //P-> 28
        remps.writeLong(0);
        //P-> 36
        //Asegurar crear folder y archivo individuales
        createEmployeeFolders(code);

    }

    private String employeeFolder(int code) {
        return "company/empleado" + code;
    }

    private void createEmployeeFolders(int code) throws IOException {
        File edir = new File(employeeFolder(code));
        edir.mkdir();
        createYearSalesFileFor(code);
    }

    private RandomAccessFile salesFileFor(int code) throws IOException {
        String dirPadre = employeeFolder(code);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);
        String path = dirPadre + "/ventas" + yearActual + ".emp";
        return new RandomAccessFile(path, "rw");

    }

    private void createYearSalesFileFor(int code) throws IOException {
        RandomAccessFile ryear = salesFileFor(code);
        if (ryear.length() == 0) {
            for (int mes = 0; mes < 12; mes++) {
                ryear.writeDouble(0);
                ryear.writeBoolean(false);
            }
        }
    }

    //Code - Name - Salary - Fecha COn.
    public void employeeList() throws IOException {
        remps.seek(0);
        //P-> 0 < ? true
        while (remps.getFilePointer() < remps.length()) {
            //P-> 0
            int code = remps.readInt();
            //P-> 4
            String name = remps.readUTF(); //Ana 8
            //p-> 12
            double salary = remps.readDouble();
            //P-> 20
            Date dateH = new Date(remps.readLong());
            //P-> 28
            if (remps.readLong() == 0) {
                System.out.println("Codigo: " + code + " Nombre: " + name
                        + "Salario: Lps. " + salary + " Contratado: " + dateH);

            }
            //P->36

        }

    }

    private boolean isEmployeeActive(int code) throws IOException {
        remps.seek(0);
        while (remps.getFilePointer() < remps.length()) {
            int codigo = remps.readInt();
            long pos = remps.getFilePointer();
            remps.readUTF();
            remps.skipBytes(16);
            if (remps.readLong() == 0 && codigo == code) {
                remps.seek(pos);
                return true;
            }
        }
        return false;
    }

    public boolean fireEmployee(int code) throws IOException {
        if (isEmployeeActive(code)) {
            String name = remps.readUTF();
            remps.skipBytes(16);
            remps.writeLong(new Date().getTime());
            System.out.println("Despidiendo a : " + name);
            return true;
        }
        return false;
    }

    public void addSaleToEmployee(int code, double monto) throws IOException {
        if (isEmployeeActive(code)) {
            RandomAccessFile sales = salesFileFor(code);
            int mes = Calendar.getInstance().get(Calendar.MONTH);
            sales.seek(mes * 18);

            double currentSales = sales.readDouble();
            sales.seek(sales.getFilePointer() - 8);
            sales.writeDouble(currentSales + monto);

            sales.readBoolean();
            sales.seek(sales.getFilePointer() - 1);
            sales.writeBoolean(true);

            System.out.println("Ventas de Lps. " + monto + " registrada para el empleado con codigo " + code);
        }
    }

    public void payEmployee(int code) throws IOException {
        if (isEmployeeActive(code)) {
            RandomAccessFile sales = salesFileFor(code);
            int month = Calendar.getInstance().get(Calendar.MONTH);

            sales.seek(month * 18 + 8);
            boolean pagado = sales.readBoolean();

            if (pagado) {
                System.out.println("Este empleado ya ha sido pagado este mes.");
                return;
            }

            remps.seek(0);
            while (remps.getFilePointer() < remps.length()) {
                int codigoEmpleado = remps.readInt();
                String name = remps.readUTF();
                double salary = remps.readDouble();
                remps.skipBytes(12);

                if (codigoEmpleado == code) {
                    sales.seek(month * 18);
                    double ventasMen = sales.readDouble();
                    double comision = ventasMen * 0.10;

                    double salarioBase = salary + comision;

                    double deduccion = salarioBase * 0.035;

                    double salarioNeto = salarioBase - deduccion;

                    File empFolder = new File(employeeFolder(code));
                    RandomAccessFile receiptFile = new RandomAccessFile(empFolder.getPath() + "/recibos.emp", "rw");
                    receiptFile.seek(receiptFile.length());

                    receiptFile.writeLong(new Date().getTime());
                    receiptFile.writeDouble(comision);
                    receiptFile.writeDouble(salarioBase);
                    receiptFile.writeDouble(deduccion);
                    receiptFile.writeDouble(salarioNeto);
                    receiptFile.writeInt(Calendar.getInstance().get(Calendar.YEAR));
                    receiptFile.writeInt(month + 1);

                    sales.seek(sales.getFilePointer() - 1);
                    sales.writeBoolean(true);

                    System.out.println("Pago realizado a " + name + " con un sueldo neto de Lps. " + salarioNeto);
                    break;
                }
            }
        }
    }

    public void printEmployee(int code) throws IOException {
        if (isEmployeeActive(code)) {
            remps.seek(0);
            while (remps.getFilePointer() < remps.length()) {
                int empCode = remps.readInt();
                String name = remps.readUTF();
                double salary = remps.readDouble();
                Date contratacion = new Date(remps.readLong());

                if (empCode == code) {
                    System.out.println("Codigo: " + empCode);
                    System.out.println("Nombre: " + name);
                    System.out.println("Salario Base: Lps. " + salary);
                    System.out.println("Fecha de Contratacion: " + contratacion);

                    RandomAccessFile ventasFile = salesFileFor(code);
                    double totalSales = 0;
                    System.out.println("Ventas Anuales:");

                    for (int contador = 0; contador < 12; contador++) {
                        ventasFile.seek(contador * 18);
                        double ventasMensuales = ventasFile.readDouble();
                        boolean processed = ventasFile.readBoolean();

                        String status;
                        if (processed) {
                            status = " procesado";
                        } else {
                            status = " no procesado";
                        }

                        System.out.println("Mes " + (contador + 1) + ": Lps. " + ventasMensuales + status);
                        totalSales += ventasMensuales;
                    }

                    System.out.println("Total de Ventas Anuales: Lps. " + totalSales);

                    File empFolder = new File(employeeFolder(code));
                    RandomAccessFile reciboFile = new RandomAccessFile(empFolder.getPath() + "/recibos.emp", "r");
                    double total = 0;
                    System.out.println("Recibos Históricos:");

                    while (reciboFile.getFilePointer() < reciboFile.length()) {
                        reciboFile.readLong();
                        reciboFile.readDouble();
                        reciboFile.readDouble();
                        reciboFile.readDouble();
                        double salarioNeto = reciboFile.readDouble();
                        reciboFile.readInt();
                        reciboFile.readInt();

                        total += salarioNeto;
                        System.out.println("Sueldo Neto: Lps. " + salarioNeto);
                    }

                    System.out.println("Total Pagado en Recibos: Lps. " + total);
                    break;
                }
            }
        }
    }
}
