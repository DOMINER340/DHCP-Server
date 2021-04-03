/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johns
 */
public class lectura {
       public static void lectura_archivo_configuracion_server( ) {
        File config = null;
        FileReader lectura = null;
        BufferedReader buffer = null;
        
        ArrayList<Red> tempRed_List = null;
        Red TempRed = null;

        try {
            config = new File(".//Config.txt");
            lectura = new FileReader(config);
            buffer = new BufferedReader(lectura);
            String linea;
            String saux;

            int cont = 0;
            while ((linea = buffer.readLine()) != null) 
            {
                //System.out.println(linea);
                StringTokenizer defaultTokenizer = new StringTokenizer(linea);
                //System.out.println("Numero de Tokens: " + defaultTokenizer.countTokens() + "\n");
                
                if( linea.equalsIgnoreCase("BEGIN"))
                {
                    System.out.println("___INICIAMOS___" + "\n");
                }
                
                if(linea.isEmpty())
                {
                    continue;
                }
                else
                {
                    saux = defaultTokenizer.nextToken();
                }
                
                if( saux.equalsIgnoreCase("dirrecion_ip_server" ) )
                {
                    byte[] server_IP = new byte[4];
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        server_IP[count] = (byte) value;
                        count++;
                    } 
                    DHCP.setServer_IP(server_IP);
                }

                
                if( saux.equalsIgnoreCase("mascara_de_red_server" ) )
                {
                    byte[] subnet_mask = new byte[4];
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        subnet_mask[count] = (byte) value;
                        count++;
                    } 
                    DHCP.setSubnet_mask(subnet_mask);
                }
                
                if( saux.equalsIgnoreCase("gateway_server" ) )
                {
                    byte[] gateway = new byte[4];
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        gateway[count] = (byte) value;
                        count++;
                    }
                    DHCP.setGateway(gateway);
                }
                
                if( saux.equalsIgnoreCase("dns_server" ) )
                {
                    byte[] dns = new byte[4];
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        dns[count] = (byte) value;
                        count++;
                    }
                    DHCP.setDns(dns);
                }
                
                
             
//                    {}
//                listaprestamos.add(aux);
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
            Logger.getLogger(DHCP.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DHCP.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public static void lectura_archivo_configuracion_redes( ) {
        ArrayList<Red> subRedes = new ArrayList<Red>();
        File config = null;
        FileReader lectura = null;
        BufferedReader buffer = null;
        
        Red TempRed = new Red();
        Red TempRed2 = new Red();

        try {
            config = new File(".//Config.txt");
            lectura = new FileReader(config);
            buffer = new BufferedReader(lectura);
            String linea;
            String saux;

            int cont = 0;
            while ((linea = buffer.readLine()) != null) 
            {
                //System.out.println(linea);
                StringTokenizer defaultTokenizer = new StringTokenizer(linea);
                //System.out.println("Numero de Tokens: " + defaultTokenizer.countTokens() + "\n");
                
                if( linea.equalsIgnoreCase("BEGIN"))
                {
                    System.out.println("___INICIAMOS pero saltamos la config del server, ya esta___" + "\n");
                }
                
                if(linea.isEmpty())
                {
                    continue;
                }
                else
                {
                    saux = defaultTokenizer.nextToken();
                }
                
                if( saux.equalsIgnoreCase("RED_1" ) )
                {
                    System.out.println("___INICIAMOS la config de la red_uno_" + "\n");
                }
                
                if( saux.equalsIgnoreCase("RED_2" ) )
                {
                    System.out.println("___INICIAMOS la config de la red_dos_" + "\n");
                }
                
                //se inicializan las variables
                ArrayList<Object> rango_direcciones_subred = new ArrayList<Object>();
                ArrayList<Object> rango_direcciones_a_entregar = new ArrayList<Object>();

                //se guardan el rango_direcciones_subred_uno
                if( saux.equalsIgnoreCase("rango_direcciones_subred_uno") )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes().toString() +"__\n");
                     
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_subred.add(tempByte);
                    
                    saux = defaultTokenizer.nextToken();
                    count = 0;
                    tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_subred.add(tempByte);
                    
                    TempRed.setRango_direcciones_subred(rango_direcciones_subred);
                }
                
                //se guardan el rango_direcciones_a_entregar_uno
                if( saux.equalsIgnoreCase("rango_direcciones_a_entregar_uno") )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes().toString() +"__\n");
                     
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_a_entregar.add(tempByte);
                    
                    saux = defaultTokenizer.nextToken();
                    count = 0;
                    tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_a_entregar.add(tempByte);
                    TempRed.setRango_direcciones_a_entregar(rango_direcciones_a_entregar);
                }
                    
                //aqui falta la parte de coipiar y pegar para rango_direcciones_a_entregar 
                    
                if( saux.equalsIgnoreCase("mascara_red_uno" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }  
                    TempRed.setMascara_red(tempByte);
                }
                
                if( saux.equalsIgnoreCase("gateway_uno" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }   
                    TempRed.setGateway(tempByte);
                }
                
                if( saux.equalsIgnoreCase("dns_uno" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }   
                    TempRed.setDns(tempByte);
                }
                
                if( saux.equalsIgnoreCase("lease_time_uno" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    TempRed.setLease_time(Integer.parseInt(saux));
                }
                
                
                //se guardan el rango_direcciones_subred_uno
                if( saux.equalsIgnoreCase("rango_direcciones_subred_dos") )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes().toString() +"__\n");
                     
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_subred.add(tempByte);
                    
                    saux = defaultTokenizer.nextToken();
                    count = 0;
                    tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_subred.add(tempByte);
                    
                    TempRed2.setRango_direcciones_subred(rango_direcciones_subred);
                }
                
                //se guardan el rango_direcciones_a_entregar_uno
                if( saux.equalsIgnoreCase("rango_direcciones_a_entregar_dos") )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes().toString() +"__\n");
                     
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_a_entregar.add(tempByte);
                    
                    saux = defaultTokenizer.nextToken();
                    count = 0;
                    tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) 
                    {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        //System.out.println("\nXXXXXXXXXXXXXyyy: ___" + value +"__\n");
                        tempByte[count] = (byte) value;
                        count++;
                    } 
                    rango_direcciones_a_entregar.add(tempByte);
                    TempRed2.setRango_direcciones_a_entregar(rango_direcciones_a_entregar);
                }
                    
                //aqui falta la parte de coipiar y pegar para rango_direcciones_a_entregar 
                    
                if( saux.equalsIgnoreCase("mascara_red_dos" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }  
                    TempRed2.setMascara_red(tempByte);
                }
                
                if( saux.equalsIgnoreCase("gateway_dos" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }   
                    TempRed2.setGateway(tempByte);
                }
                
                if( saux.equalsIgnoreCase("dns_dos" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    int count = 0;
                    byte[] tempByte = new byte[4];
                    for (StringTokenizer stringTokenizer = new StringTokenizer(saux,"."); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        int value = Integer.parseInt(token);
                        tempByte[count] = (byte) value;
                        count++;
                    }   
                    TempRed2.setDns(tempByte);
                }
                
                if( saux.equalsIgnoreCase("lease_time_dos" ) )
                {
                    saux = defaultTokenizer.nextToken();
                    //System.out.println("\nLinea inside: ___" + saux.getBytes() +"__\n");
                    TempRed2.setLease_time(Integer.parseInt(saux));
                }
                cont = 0;
            }
            
            //aqui aparentemente se guarda la red_uno como temp_Red
            subRedes.add(TempRed);
            subRedes.add(TempRed2);
            
            
            
            lectura.close();
            buffer.close();
            DHCP.setSubRedes(subRedes);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DHCP.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DHCP.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
