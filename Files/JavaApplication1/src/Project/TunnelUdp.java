package Project;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TunnelUdp {

    private DatagramSocket socketUDP;

    public TunnelUdp()
    {
        
    }
            
            
  public void Escuchar() {

    try {
      socketUDP = new DatagramSocket(67);
      
      byte[] bufer = new byte[1000];

      while (true) {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);
        

        System.out.print("Datagrama recibido del host: " +
                           peticion.getAddress());
        System.out.println(" desde el puerto remoto: " +
                           peticion.getPort());
        int longitud = peticion.getLength();
        DHCP.dataRecibida(peticion.getData(),longitud,peticion.getPort());
      }
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
 
  }
  
  public void enviar(byte[] temp_data, byte[]  longitud,int port,byte[] mac)
  {
        try {
        
            byte[] buffer = new byte[600];
            InetAddress addr = InetAddress.getByName("192.168.0.7");
   
            //DatagramSocket socketUDP_f = new DatagramSocket(68);
            DatagramPacket temp = new DatagramPacket(temp_data,0,temp_data.length  ,addr,68);
      
            socketUDP.send(temp);
        } catch (IOException ex) {
            Logger.getLogger(TunnelUdp.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

}