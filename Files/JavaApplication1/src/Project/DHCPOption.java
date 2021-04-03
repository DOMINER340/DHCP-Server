package Project;


import java.util.ArrayList;


public class  DHCPOption{

	/*
        Unique option, might contains multiples values for each option
        */
	private static final int DHCPMESSAGETYPE = 53;
        //DHCP Parameter Request List
        private static final int REQUESTLIST = 55;      
        //DHCP Server Identifiers
	private static final int DHCPSERVERID = 54;
	//DHCP Client Identifiers
	private static final int DHCPCLIENTID = 61;
        //DHCP Lease time
	private static final int LEASETIME = 51;
        //DHCP Renovation time
	private static final int RENOVATIONTIME = 58;
        //DHCP SubnetMask
	private static final int SUBNETMASK = 1;
        //DHCP Domaine Name Server
	private static final int DNS = 6;
        //DHCP Router-Gateway
	private static final int ROUTER = 3;
        //DHCP END
	private static final int END = 255;
        
        //DHCP Message Types
	private static final int DHCPDISCOVER = 1;
	private static final int DHCPOFFER = 2;
	private static final int DHCPREQUEST = 3;
	private static final int DHCPACK = 5;
        
        private ArrayList<ArrayList<Object>> ListofList ;

    static ArrayList<Object> crear_opcion_53(Integer tipo) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 53;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 1;
        byte temp_uno = lenght.byteValue();
        
        Integer message_type = tipo; 
        byte temp_dos = message_type.byteValue();
        
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( temp_dos );
        
        return option_temp;
    }
    
     static ArrayList<Object> crear_opcion_1(byte[] mascara) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 1;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();
        
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( mascara);
        
        return option_temp;
    }
    
     static ArrayList<Object> crear_opcion_3(byte[] puerta_enlace) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 3;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();
              
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( puerta_enlace );
        
        return option_temp;
    }
    
     static ArrayList<Object> crear_opcion_6 (byte[] ip_server) 
    {
        
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 6;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();
               
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( ip_server);
        
        return option_temp;
    }
     
    static ArrayList<Object> crear_opcion_54 (byte[] ip_server) 
    {
        
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 54;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();
               
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( ip_server);
        
        return option_temp;
    }
     
     static ArrayList<Object> crear_opcion_61 (byte[] mac) 
    {
        
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer dato_uno = 61;
        byte temp_cero = dato_uno.byteValue();
        
        Integer lenght = 7;
        byte temp_uno = lenght.byteValue();
        
        
        Integer tipo_hardware = 1;
        byte temp_dos = tipo_hardware.byteValue();
               
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( temp_dos );
        option_temp.add( mac);
        
        return option_temp;
    }
         static ArrayList<Object> crear_opcion_58(int tiempo_prestamo) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();

        Integer dato_uno = 58;
        byte temp_cero = dato_uno.byteValue();

        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();

        int x = tiempo_prestamo / 2;
        byte[] dword = new byte[4];
        dword[0] = (byte) ((x >> 24) & 0x000000FF);
        dword[1] = (byte) ((x >> 16) & 0x000000FF);
        dword[2] = (byte) ((x >> 8) & 0x000000FF);
        dword[3] = (byte) (x & 0x00FF);
          
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( dword );

        return option_temp;
    }
         
         
    static ArrayList<Object> crear_opcion_51(int tiempo_prestamo) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();

        Integer dato_uno = 51;
        byte temp_cero = dato_uno.byteValue();

        Integer lenght = 4;
        byte temp_uno = lenght.byteValue();

  
        byte[] dword = new byte[4];
        dword[0] = (byte) ((tiempo_prestamo >> 24) & 0x000000FF);
        dword[1] = (byte) ((tiempo_prestamo >> 16) & 0x000000FF);
        dword[2] = (byte) ((tiempo_prestamo >> 8) & 0x000000FF);
        dword[3] = (byte) (tiempo_prestamo & 0x00FF);
          
        option_temp.add( temp_cero );
        option_temp.add( temp_uno );
        option_temp.add( dword );

        return option_temp;
    }
      
     static ArrayList<Object> crear_opcion_255 ( ) 
    {
        ArrayList<Object> option_temp = new ArrayList<>();
        
        Integer Objeto1 = 255;
        byte temp_uno = Objeto1.byteValue();
        
        option_temp.add( temp_uno );
        
        return option_temp;
    }
        
        
        
        
        
        public DHCPOption ()
        {
            
        }
        
        
        

    public ArrayList<ArrayList<Object>> getListofList() {
        return ListofList;
    }

    public void setListofList(ArrayList<ArrayList<Object>> ListofList) {
        this.ListofList = ListofList;
    }

    void imprimirOpciones(ArrayList<ArrayList<Object>> listofList) 
    {
        //verifiquemos que la informacion realmente este
        for (ArrayList<Object> Temp : listofList)
        {
            System.out.println("\n tipoM:___"+ Temp.get(0) + "__ longitud:___"+Temp.get(1) + "___\n");
        }
    }
        
}