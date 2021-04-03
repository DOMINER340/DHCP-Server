package Project;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author johns
 */
public class Responder {

    private Message respuesta = new Message();

    void responderDiscover(Message recibido,int port) {
        int auxe;
        Integer x;
        byte temporal;
        byte[] tempByte4 = new byte[4];
        byte[] tempByte2 = new byte[2];
        byte[] tempByte16 = new byte[16];
        byte[] tempByte64 = new byte[64];
        byte[] tempByte128 = new byte[128];

        //se modifica el valor de tipo mensaje
        x = 2;
        temporal=x.byteValue();
        respuesta.setOp(temporal);
        
        //se modifica el valor de tipo de hardware
        x = 1;
        temporal=x.byteValue();
        respuesta.sethType(temporal);
        
        //Se modifica el valor de la longitud de hadware
        x = 6;
        temporal=x.byteValue();
        respuesta.sethLen(temporal);
        
        //Se modifica el valor Hardware options
        x = 0;
        temporal=x.byteValue();
        respuesta.sethOps(temporal);
        
        
        //Se modifica el valor del id de la transaccion 
        int temp = ByteBuffer.wrap(recibido.getxId()).getInt();
        System.out.println("\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx___:" + temp + "___\n");
        temp++;
        System.out.println("\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx___:" + temp + "___\n");
        tempByte4 = ByteBuffer.allocate(4).putInt(temp).array();
        respuesta.setxId(tempByte4);
        
        //Se modifica el valor de segundos desde que se presto la ip
        x = 0;
        tempByte2[0] = x.byteValue();
        tempByte2[1] = x.byteValue();
        respuesta.setSecs(tempByte2);
        
        //Se modifica el valor de los flags del boot
        int a = 128;
        int b = 0;
        tempByte4 = new byte[2];
        tempByte4[0] = (byte)  a;
        tempByte4[1] = (byte)  b;
        respuesta.setFlags(tempByte4);
        
        //Se modifica el valor de la ip del cliente   
        respuesta.setCiAddr(recibido.getCiAddr());
        
        //Aqui tenemos que colocar la logica de la entrega de las direcciones ip -> Valor Your Ip
         a = 192;
         b = 168;
        int c = 0;
        int d = 5;
        tempByte4 = new byte[4];
        tempByte4[0] = (byte)  a;
        tempByte4[1] = (byte)  b;
        tempByte4[2] = (byte)  c;
        tempByte4[3] = (byte)  d;
        respuesta.setYiAddr(tempByte4);
        //Hasta aqui va la logica
        
        //Se modifica el valor de Server IP 
        respuesta.setSiAddr(DHCP.getServer_IP());
        
        //Se modifica el valor de la IP relay 
        //CUIDADO CON LO QUE COLOCAMOS AQUI
         a = 0;
         b = 0;
         c = 0;
         d = 0;
        tempByte4 = new byte[4];
        tempByte4[0] = (byte)  a;
        tempByte4[1] = (byte)  b;
        tempByte4[2] = (byte)  c;
        tempByte4[3] = (byte)  d;
        respuesta.setGiAddr(tempByte4);
        
        //Se modifica el valor de la MAC Cliente 
        respuesta.setChAddr(recibido.getChAddr());
        
         //Se modifica el valor del nombre del servidor 
        respuesta.setsName(recibido.getsName());
        
        //Se modifica el valor del nombre del archivo
        respuesta.setFile(recibido.getFile());
        
        //Se modifica el valor de la magic cookie
        respuesta.setMagic_cookie(recibido.getMagic_cookie());

        ArrayList<ArrayList<Object>> ListofList_temp = new ArrayList<>();
        DHCPOption temp_opciones = respuesta.getOpciones();
        
        ArrayList<Object> option_temp = new ArrayList<>();
        
        //vamos a crear la opcion 53
        Integer F = 2;
        option_temp = DHCPOption.crear_opcion_53(F);
        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 51
        option_temp = DHCPOption.crear_opcion_54(DHCP.getServer_IP());
        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 51
        option_temp = DHCPOption.crear_opcion_51(600);
        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 58
        option_temp = DHCPOption.crear_opcion_58(600);
        ListofList_temp.add(option_temp);
        
        
        //vamos a crear la opcion 1
        //&&aqui toca validar que red es a que nos envia la solicitud,
        //pra pasarle correctamente la mascara respectiva
        //esto lo podemos hacer mediante el uso o no del relay en el mensaje discover
        option_temp = DHCPOption.crear_opcion_1(DHCP.getSubnet_mask());
        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 6
        option_temp = DHCPOption.crear_opcion_6(DHCP.getServer_IP());
        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 3
        option_temp = DHCPOption.crear_opcion_3(DHCP.getGateway());
        ListofList_temp.add(option_temp);
//        
//        //vamos a crear la opcion 61
//        option_temp = DHCPOption.crear_opcion_61(recibido.getChAddr());
//        ListofList_temp.add(option_temp);
        
        //vamos a crear la opcion 255
        option_temp = DHCPOption.crear_opcion_255();
        ListofList_temp.add(option_temp);
        
        //añado toda la lista de opciones creadas a la variable de opciones de mensaje
        temp_opciones.setListofList(ListofList_temp);

        
        System.out.println("\n---------------------------------------------------------------------------------------\n");
        byte[] temporal_arreglo = respuesta.crearArreglo();
        System.out.println("\n---------------------------------------------------------------------------------------\n");
        
        byte[] buffer = new byte[temporal_arreglo.length];
        DHCP.enviarOffer(temporal_arreglo,buffer,port,recibido.getChAddr());
        
        
        byte[] trama_ethernet = new byte[14];

        byte[] mac_origen = new byte[6];
     
        
        byte[] mac_destino = new byte[6];
        mac_origen=recibido.getChAddr();
        
        byte[] type = new byte[2];
        
        char ip_byte[] = 
        {
            0x45, 0x00, 0x01, 0x48, 
            0x40, 0x77, 0x00, 0x00,
            0x40, 0x11, 0xc6, 0x8f,
            
            0xc0, 0xa8, 0x78, 0xfe,
            
            0xc0, 0xa8, 0x78, 0x4f
        };
        
        char udp[] = 
        {
            0x00, 0x43, 0x00, 0x44, 0x01, 0x34, 0xaf, 0x48
        };
        
    }

    void responderRequest(Message recibido , int port) {

    }

}
