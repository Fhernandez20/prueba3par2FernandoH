/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.io.FileWriter;

/**
 *
 * @author josue
 */
public class MyFile {

    private File mifile = null;

    void setFile(String direccion) {
        mifile = new File(direccion);

    }

    void info() {
        if (mifile.exists()) {
            System.out.println("\nNombre: " + mifile.getName());
            System.out.println("Path: " + mifile.getPath());
            System.out.println("Absoluta: " + mifile.getAbsolutePath());
            System.out.println("Bytes: " + mifile.length());
            System.out.println("Modificado en: " + new Date(mifile.lastModified()));
            System.out.println("Padre: " + mifile.getAbsoluteFile().getParentFile().getName());
            if (mifile.isFile()) {
                System.out.println("ES FILE");

            } else if (mifile.isDirectory()) {
                System.out.println("ES FOLDER");
            }
            System.out.println("-+-+-+-+-+-+-+-+-");
        } else {
            System.out.println("el archivo no existe");
        }

    }

    boolean crearFile() throws IOException {
        return mifile.createNewFile();

    }

    boolean crearFolder() {
        return mifile.mkdirs();
    }

    void borrar() {
        if (antidoto(mifile)) {
            System.out.println("Borrado!");
        } else {
            System.out.println("No se pudo borrar!");
        }
    }

    private boolean antidoto(File mf) {
        if (mf.isDirectory()) {
            for (File child : mf.listFiles()) {
                antidoto(child);
            }

        }
        return mf.delete();
    }

    void dir() {
        if (mifile.isDirectory()) {
            System.out.println("Directorio de: " + mifile.getAbsolutePath());
            System.out.println("");

            //Contadores
            int cfiles = 0, cdirs = 0, tbytes = 0;
            //Recorrido
            for (File child : mifile.listFiles()) {
                if (!child.isHidden()) {
                    //Ultima Modificacion
                    Date ultimo = new Date(child.lastModified());
                    System.out.print(ultimo + "\t");
                    //Si es file o folder
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.print("<DIR>\t\t");
                    } else {
                        cfiles++;
                        tbytes += child.length();
                        System.out.print("    \t" + child.length() + "\t");

                    }
                    System.out.println(child.getName());
                }
            }
            System.out.println(cfiles + " archivos\t" + tbytes + " bytes");
            System.out.println(cdirs + " dirs\t");
        }
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "--");
                }
            }
        }
    }

    void tree() {
        tree(mifile, "-");
    }

    public boolean reescribir(String texto) {
        if (mifile.exists() && mifile.isFile()) {
            try (FileWriter writer = new FileWriter(mifile, false)) {
                writer.write(texto);
                System.out.println("Texto reescrito correctamente.");
                return true;
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido.");
            return false;
        }
    }

    public boolean addinfo(String texto) {
        if (mifile.exists() && mifile.isFile()) {
            try (FileWriter writer = new FileWriter(mifile, true)) {
                writer.write(texto);
                System.out.println("Texto anadido correctamente.");
                return true;
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo valido.");
            return false;
        }
    }

    public void leerArchivoReader() {
        if (mifile.exists() && mifile.isFile()) {
            try (FileReader fr = new FileReader(mifile); BufferedReader br = new BufferedReader(fr)) {
                String linea;
                System.out.println("Contenido del archivo:");
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo valido.");
        }
    }
    
    public void leerArchivobuffer() {
    if (mifile.exists() && mifile.isFile()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(mifile))) {
            String line;
            System.out.println("Contenido del archivo:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    } else {
        System.out.println("El archivo no existe o no es un archivo valido.");
    }
}
}
