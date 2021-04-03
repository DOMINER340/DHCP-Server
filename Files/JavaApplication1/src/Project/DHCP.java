package Project;



import java.util.ArrayList;



/**
 *
 * @author pruebas
 */
public class DHCP {
    //private static final String Archivo_configTxt = ".//Config.txt";
    private static byte[] server_IP = new byte[4];
    private static byte[] subnet_mask = new byte[4];
    private static byte[] gateway = new byte[4];
    private static byte[] dns = new byte[4];
    private static ArrayList<Object> OBJETOS;
    private static ArrayList<Red> subRedes = new ArrayList<Red>();
//    private static ArrayList<Object> ipListS1 = new ArrayList<Object>();
//    private static ArrayList<Object> ipListS2 = new ArrayList<Object>();
    private static Responder responder;
    private static TunnelUdp UdpMain;

    private static void imprimir_estado_configuracion() {
            //verificacion profunda de los datos guardados en la variable server_IP
            System.out.println("\n server_IP:___" 
                                +Byte.toUnsignedInt(server_IP[0]) + "." +
                                +Byte.toUnsignedInt(server_IP[1]) + "." +
                                +Byte.toUnsignedInt(server_IP[2]) + "." +
                                +Byte.toUnsignedInt(server_IP[3])+"___\n");
            
            System.out.println("\n subnet_mask:___" 
                                +Byte.toUnsignedInt(subnet_mask[0]) + "." +
                                +Byte.toUnsignedInt(subnet_mask[1]) + "." +
                                +Byte.toUnsignedInt(subnet_mask[2]) + "." +
                                +Byte.toUnsignedInt(subnet_mask[3])+"___\n");
            
            System.out.println("\n gateway:___" 
                                +Byte.toUnsignedInt(gateway[0]) + "." +
                                +Byte.toUnsignedInt(gateway[1]) + "." +
                                +Byte.toUnsignedInt(gateway[2]) + "." +
                                +Byte.toUnsignedInt(gateway[3])+"___\n");
            
            System.out.println("\n dns:___" 
                                +Byte.toUnsignedInt(dns[0]) + "." +
                                +Byte.toUnsignedInt(dns[1]) + "." +
                                +Byte.toUnsignedInt(dns[2]) + "." +
                                +Byte.toUnsignedInt(dns[3])+"___\n");
            
            imprimir_Lista_de_redes();
    }

    private static void imprimir_Lista_de_redes() {
        int contador =1;
        for (Red subRed : subRedes) 
        {
             
            ArrayList<Object> rango_direcciones_subred ;
            ArrayList<Object> rango_direcciones_a_entregar ;
            byte[] mascara_red =  new byte [4] ;
            byte[] gateway  =new byte [4] ;
            byte[] dns  = new byte [4] ;
            byte[] aux=new byte[4];
            byte[] aux2=new byte[4];
            int lease_time;
            rango_direcciones_subred = subRed.getRango_direcciones_subred();
            rango_direcciones_a_entregar = subRed.getRango_direcciones_a_entregar();
            mascara_red = subRed.getMascara_red();
            gateway = subRed.getGateway();
            dns = subRed.getDns();
            lease_time = subRed.getLease_time();
            
            System.out.println("\n___RED " + contador + "___\n" );
             aux=(byte[]) rango_direcciones_subred.get(0);
             aux2=(byte[]) rango_direcciones_subred.get(1);
             System.out.println("\n rango_de_direcciones_subred1_" + ":___" 
                                +Byte.toUnsignedInt(aux[0]) + "." +
                                +Byte.toUnsignedInt(aux[1]) + "." +
                                +Byte.toUnsignedInt(aux[2]) + "." +
                                +Byte.toUnsignedInt(aux[3]) +"___Hasta__"+
                                +Byte.toUnsignedInt(aux2[0]) + "."+
                                +Byte.toUnsignedInt(aux2[1]) + "." +
                                +Byte.toUnsignedInt(aux2[2]) + "."+
                                +Byte.toUnsignedInt(aux2[3])+"___\n");
            
             aux=(byte[]) rango_direcciones_a_entregar.get(0);
             aux2=(byte[]) rango_direcciones_a_entregar.get(1);
             System.out.println("\n rango_de_direcciones_a_entregar" + ":___" 
                                +Byte.toUnsignedInt(aux[0]) + "." +
                                +Byte.toUnsignedInt(aux[1]) + "." +
                                +Byte.toUnsignedInt(aux[2]) + "." +
                                +Byte.toUnsignedInt(aux[3]) +"___Hasta__"+
                                +Byte.toUnsignedInt(aux2[0]) + "."+
                                +Byte.toUnsignedInt(aux2[1]) + "." +
                                +Byte.toUnsignedInt(aux2[2]) + "."+
                                +Byte.toUnsignedInt(aux2[3])+"___\n");
                
            System.out.println("\n mascara_red_" + ":___" 
                                +Byte.toUnsignedInt(mascara_red[0]) + "." +
                                +Byte.toUnsignedInt(mascara_red[1]) + "." +
                                +Byte.toUnsignedInt(mascara_red[2]) + "." +
                                +Byte.toUnsignedInt(mascara_red[3])+"___\n");
            System.out.println("\n gateway:___" 
                                +Byte.toUnsignedInt(gateway[0]) + "." +
                                +Byte.toUnsignedInt(gateway[1]) + "." +
                                +Byte.toUnsignedInt(gateway[2]) + "." +
                                +Byte.toUnsignedInt(gateway[3])+"___\n");
            System.out.println("\n dns:___" 
                                +Byte.toUnsignedInt(dns[0]) + "." +
                                +Byte.toUnsignedInt(dns[1]) + "." +
                                +Byte.toUnsignedInt(dns[2]) + "." +
                                +Byte.toUnsignedInt(dns[3])+"___\n");
            System.out.println("\n lease_time:___" 
                                +lease_time + "___\n");
            
            contador++;
        }
    }

    static ArrayList<Object> crear_opcion_53() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void enviarOffer(byte[] temporal_arreglo,byte[]  longitud,int port,byte[] mac) {
        UdpMain.enviar(temporal_arreglo, longitud,port,mac);
    }
    
    //constructor
    public DHCP() {};
            
    //funcion principal de la clase
    public static void main(String[] args)
    {
       
        DHCP dhcpMain = new DHCP();
        responder = new Responder();
        //aqui leo desde el documento 
        // y aplico los set de los 4 paraemtros de arriba
        lectura.lectura_archivo_configuracion_server();
        lectura.lectura_archivo_configuracion_redes();
        //Funcion guardar las lista de direcciones IP
        imprimir_estado_configuracion();
        
        ArchivoLog.realizar_prueba_log();
        
        UdpMain = new TunnelUdp();
        OBJETOS = new ArrayList<Object>() ;
        UdpMain.Escuchar();
        
    }
    
 

    static void dataRecibida(byte[] data, int longitud,int port) {
        int cont=0;
        int type=0;
        //String temp = new String(data[0]);
        System.out.print(" Longitud: ___" + longitud + "___\n" );
        Message recibido = new Message();
        String temp = "";
        recibido.setOp(data[0]);
        recibido.sethType(data[1]);
        recibido.sethLen(data[2]);
        recibido.sethOps(data[3]);
        cont=+3;
        
        for(int i=0; i<4; i++){
            cont++;
            recibido.setxId(data[cont],i);
        }
     
        for(int i=0; i<2; i++){
            cont++;
            recibido.setSecs(data[cont],i);
        }
         
        for(int i=0; i<2; i++){
            cont++;
            recibido.setFlags(data[cont],i);
        }
        
        for(int i=0; i<4; i++){
            cont++;
            recibido.setCiAddr(data[cont],i);
        }
        
        for(int i=0; i<4; i++){
            cont++;
            recibido.setYiAddr(data[cont],i);
        }
        
        for(int i=0; i<4; i++){
            cont++;
            recibido.setSiAddr(data[cont],i);
        }
        
        for(int i=0; i<4; i++){
            cont++;
            recibido.setGiAddr(data[cont],i);
        }
        
        for(int i=0; i<16; i++){ //MAC: 6 y Relleno:10
             cont++;
            recibido.setChAddr(data[cont],i);
        }
               
        for(int i=0; i<64; i++){
            cont++;
            recibido.setsName(data[cont],i);
        }
        
         for(int i=0; i<128; i++){
            cont++;
            recibido.setFile(data[cont],i);
        }
        
         for(int i=0; i<4; i++){
            cont++;
            recibido.setMagic_cookie(data[cont],i);
        }
        //hasta aqui la lectura de datos esta perfecta... no tocar.
        
        //leer opcion 53 - que aparentemente siempre va primero
        DHCPOption tempOption = recibido.getOpciones();
        ArrayList<ArrayList<Object>> listofList = new ArrayList<ArrayList<Object>>();
        
        //se ubica en el siguiente dato
        cont++;
        
        while( Byte.toUnsignedInt(data[cont]) != 255 )
        {
            ArrayList<Object> objeto_temp;
            objeto_temp = new ArrayList<>();

            int temporal = Byte.toUnsignedInt(data[cont]);
                  
            //System.out.println("\n entre pro:___" + Byte.toUnsignedInt(data[cont]) +"___\n");
            objeto_temp.add( data[cont] );
            cont++;
            objeto_temp.add( data[cont] );
            
            int length = Byte.toUnsignedInt(data[cont]);
            
            //si la opcion es la 53 entonces pasara esto 
            if( temporal == 53 )
            {
                //aqui verifico el tipo de mensaje de la opcion 53
                int X = cont + 1;
                objeto_temp.add( data[X] );
            } 
            
            for (int i = 0; i < length; i++)
            {
                cont ++;
            } 
            

            
            listofList.add(objeto_temp);
            cont ++;
        }
        
        if(Byte.toUnsignedInt(data[cont]) == 255 )
        {
            //aqui se hace enfasis en que se encontro la opcion 255
            System.out.println("\n entre a la opcion 255 \n");
        }
        
        //se modifica la variable del padding
        if( (longitud-1-cont) > 0 )
        {
            byte[] pad = new byte[(longitud-1-cont)];
            recibido.setPadding(pad);
        }
        
        //se guarda la lista de listas
        tempOption.setListofList(listofList);
        recibido.setOpciones(tempOption);
                
        recibido.imprimir();

        tempOption.imprimirOpciones(recibido.getOpciones().getListofList());
        //aqui toca mirar cual es la logica del padding 
        System.out.println("\n padding:___"+ (longitud-1-cont)+ "\n");
        
        
        System.out.println("\n tamaño listoflist:___"+ listofList.size() + "\n");
        //guardo todos los datos aqui modiicados, 
        //como una variable objetos dentro de nuestro arreglo de objetos
        OBJETOS.add(recibido);
        
        //se analiza que tipo de mensaje es y se llama la funcion a la que le corresponda responder
        for (ArrayList<Object> Temp : listofList)
        {
            System.out.println("\n tipoM:___"+ Temp.get(0) + "___\n");
            int x;
            byte T;
            T = (byte) Temp.get(0);
            x = Byte.toUnsignedInt(T);
            System.out.println("\n tipoM:_INT INT __"+ x + "___\n");
            //aqui verifico el tipo de mensaje de la opcion 53
            if( x == 53 )
            {
                T = (byte) Temp.get(2);
                x = Byte.toUnsignedInt(T);
                System.out.println("\n tipoM:_INT 2 INT 2 __"+ x + "___\n");
                // se llama la funcion respectiva para responder
                if( x == 1)
                {
                    System.out.println("\n__------------entro-----__\n");
                    //mensaje discover detectado
                    responder.responderDiscover(recibido,port);
                }
                if( x == 3)
                {
                    //mensaje request detectado
                    responder.responderRequest(recibido,port);
                }

                //aqui se podria verificar si el tipo de mensaje recibiod es para renovar la ip
                
            }    
        }
            
    }
    
    
    
   
    
    
    public static void setServer_IP(byte[] server_IP) {
        DHCP.server_IP = server_IP;
    }

    public static void setSubnet_mask(byte[] subnet_mask) {
        DHCP.subnet_mask = subnet_mask;
    }

    public static void setGateway(byte[] gateway) {
        DHCP.gateway = gateway;
    }

    public static void setDns(byte[] dns) {
        DHCP.dns = dns;
    }

    public static void setSubRedes(ArrayList<Red> subRedes) {
        DHCP.subRedes = subRedes;
    }

    public static byte[] getServer_IP() {
        return server_IP;
    }

    public static byte[] getSubnet_mask() {
        return subnet_mask;
    }

    public static byte[] getGateway() {
        return gateway;
    }

    public static byte[] getDns() {
        return dns;
    }    
}
