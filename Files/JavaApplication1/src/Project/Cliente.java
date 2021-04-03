package Project;

import java.io.*;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import com.sun.jndi.cosnaming.IiopUrl;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;   
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class Cliente {
    
    public static final int PORT = 67;
    public static final String SERVER_UBICACION = "localhost";
    
    private DatagramSocket clienteSocket;
    
    private InetAddress direccionIPservidor;
    private int puertoServidor;
    
    private DatagramPacket packeteEnviar;
    private DatagramPacket packeteRecibir;
    
    private byte [] bufferRecibir;
    
//Construtor 

public Cliente ()
{
//Espacio de memoria utilizado para recibir datos
bufferRecibir = new byte [1024];

System.out.println("Cliente UDP");

try
{
    //Creacion del socket de datagrama en el lado cliente.
    //Observe que en este caso no es necesario identificar el servidor en el momento de 
    //Crear el socket.
    clienteSocket = new DatagramSocket();
    
    //Identificacion del servidor 
   direccionIPservidor = InetAddress.getByName(SERVER_UBICACION);
   
   String mensaje = JOptionPane.showInputDialog(null, "Ingrese un mensaje:");
   
   System.out.println("Enviado" + mensaje);
   System.out.println("\nPUERTO" + clienteSocket.getPort());
   //Envio del mensaje al servidor
   send (mensaje);
   DatagramSocket socketUDP = new DatagramSocket(5000);

   //Recepcion del mensaje de respuesta envia por el servidor.
    while (true) {   
    
    String respuesta = (String) receive();
    socketUDP.receive(packeteRecibir);

   
    System.out.println("Recibido" + respuesta);
     
    }

    //
}
//Puede lanzar una excepcion de socket
catch (SocketException e)
{
    e.printStackTrace();
}
//Puede lanzar una excepcion de host desconocido
catch (UnknownHostException e)
{
    e.printStackTrace();
}
//Puede lanzar una excepcion de entrada y salida
catch (IOException e)
{
    e.printStackTrace();
}
}
/**
 * Este metodo permite enviar un mensaje al servidor
 * @param messageToSend Reibe por parametro el mensaje que desea enviar.
 * @throws IO
 */
private void send (String messageToSend) throws IOException
{
    //convierte el String en un byte[]
    byte [] ba = messageToSend.getBytes();
    
    /**
     * Crear el paquete a enviar con el mensaje en forma de byte[],la
     * direccion IP del servidor y el numero de puerto
     */
    packeteEnviar = new DatagramPacket(ba, ba.length, direccionIPservidor, PORT);
    
    
    //Envia el paquete por el socket
    clienteSocket.send(packeteEnviar);
    
    
}

/**
 * Este metodo permite recibir un mensaje enviado por el servidor
 * @throws IOException
*/
private String receive ()throws IOException
{

    //Crea el paquete a recibir el mensaje de respuesta del servidor
    packeteRecibir = new DatagramPacket(bufferRecibir, bufferRecibir.length);
    
    //Recibe el paquete enviado por el servidor
   clienteSocket.receive(packeteRecibir);
        
    
    //Convierte a String el contenido del campo de datos del paquete recibido.
    String mensajeRecibido = new String(packeteRecibir.getData(),0,packeteRecibir.getLength());
    
    return mensajeRecibido;   
}
/**
 * Metodo principal utilizado para lanzar el programa cliente.
 *
 */
    public static void main(String[] args) {
        
        new Cliente();
   
    }
}