/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ArchivoLog {
    static final String NArchivo=".\\configuracion.config"; 
    /**
     * @param args the command line arguments
     */
    
    //esta funcion se debe modificar al final de todo 
    //para que se adapte correctamente a lo que queremos mostrar
    public static void realizar_prueba_log() {
      /*  Registro_data_ip p1=new Registro_data_ip("10.0.0.15","08:00:27:8F:1F:D5","255.0.0.0","10.0.0.1","87.216.1.65","3500","24/11/20","12/08/22");
        //Se instacia un Registro_data_ip y se le envia a la funcion que guarda la informacion en el archivo 
        escritura_archivo(p1);
        Registro_data_ip p2=new Registro_data_ip("10.0.0.30","10:FF:00:00:EF:00","255.0.0.0","10.0.0.1","87.216.1.65","5000","01/15/20","15/08/22");
        escritura_archivo(p2);
        Registro_data_ip p3=new Registro_data_ip("10.0.0.40","XX:XX:XX:XX:XX:XX","255.0.0.0","10.0.0.1","87.216.1.65","20000","24/11/20","15/12/21");
        escritura_archivo(p3);
        Registro_data_ip p4=new Registro_data_ip("10.0.0.50","08:00:27:8F:1F:D5","255.0.0.0","10.0.0.1","87.216.1.65","150","24/11/20","21/08/20");
        escritura_archivo(p4);*/
        
        lectura_archivo();
    }

    public static void escritura_archivo(Registro_data_ip info) {
        File archivo;
        archivo = new File(NArchivo);
        try 
        {
            String infoescribir=info.IP+" "+info.MAC+" "+info.MSub+" "+
                                info.PEnlace+" "+info.DNS+" "+info.TPrestamo+" "+info.FPrestamo+" "+info.FExpiracion;
            FileWriter archivow = new FileWriter(archivo, true);
            BufferedWriter bufferw = new BufferedWriter(archivow);
            PrintWriter escritura = new PrintWriter(bufferw);
            escritura.append(infoescribir+"\n"); //concatenamos en el archivo sin borrar lo existente
            escritura.close();
            bufferw.close();
        } catch (IOException e) {
        };
    }

    public static void lectura_archivo( ) {
        File config ;
        FileReader lectura ;
        BufferedReader buffer ;
        Registro_data_ip aux = new Registro_data_ip();
        ArrayList<Registro_data_ip> listaprestamos = new ArrayList<Registro_data_ip>();

        try {
            config = new File(NArchivo);
            lectura = new FileReader(config);
            buffer = new BufferedReader(lectura);
            String linea;
            String saux;

            int cont = 0;
            while ((linea = buffer.readLine()) != null) {
                System.out.println(linea);
                StringTokenizer defaultTokenizer = new StringTokenizer(linea);
                System.out.println("Numero de Tokens: " + defaultTokenizer.countTokens());
                while (defaultTokenizer.hasMoreTokens()) {
                    saux = defaultTokenizer.nextToken();
                    switch (cont) {
                        case 0:
                            aux.IP = saux;
                            break;
                        case 1:
                            aux.MAC = saux;
                            break;
                        case 2:
                            aux.MSub = saux;
                            break;
                        case 3:
                            aux.PEnlace = saux;
                            break;
                        case 4:
                            aux.DNS = saux;
                            break;
                        case 5:
                            aux.TPrestamo = saux;
                            break;
                        case 6:
                            aux.FPrestamo = saux;
                            break;
                        case 7:
                            aux.FExpiracion = saux;
                            break;
                        default:
                            break;
                    }
                    cont++;
                }
                listaprestamos.add(aux);
//                JOptionPane.showMessageDialog(null, "Direccion IP " + aux.IP + "\n"
//                        + "Direccion MAC" + aux.MAC + "\n"
//                        + "Macara de Subred " + aux.MSub + "\n"
//                        + "Puerta de Enlace " + aux.PEnlace + "\n"
//                        + "DNS " + aux.DNS + "\n"
//                        + "Tiempo Prestamo " + aux.TPrestamo + "\n"
//                        + "Fecha Prestamo " + aux.FPrestamo + "\n"
//                        + "Fecha de Finalizacion " + aux.FExpiracion);
                cont = 0;
            }
            lectura.close();
            buffer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoLog.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoLog.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
