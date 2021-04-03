/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author pruebas
 */
public class Message {

    // this type
    private byte op;

    //Hardware type
    private byte hType;

    //Hardware address length 
    private byte hLen;

    //Hops
    private byte hOps;

    //Transaction ID
    private byte[] xId = new byte[4];

    //Seconds elapsed
    private byte[] secs = new byte[2];

    //BOOTP flags
    private byte[] flags = new byte[2];

    //Client IP
    private byte[] ciAddr = new byte[4];

    //Your IP
    private byte[] yiAddr = new byte[4];

    //Server IP
    private byte[] siAddr = new byte[4];

    //Relay IP
    private byte[] giAddr = new byte[4];

    //Client MAC
    private byte[] chAddr = new byte[16];

    //Server hostname 
    private byte[] sName = new byte[64];

    //Boot filename
    private byte[] file = new byte[128];

    //magic cookie
    private byte[] magic_cookie = new byte[4];

    private DHCPOption opciones = new DHCPOption();

    private byte[] padding = null;

    public byte[] crearArreglo() {
        //Suponemos que esta es la logica para el tamaño de las opciones 240+Opciones+Padding
        
         ArrayList<ArrayList<Object>> listofList = opciones.getListofList();
        int sum = 0;
        for (ArrayList<Object> dato : listofList) 
        {
            if(Byte.toUnsignedInt((byte) dato.get(0)) != 255)
            {
              sum = sum +2;
              sum = sum + Byte.toUnsignedInt((byte) dato.get(1));
            }
            else
            {
                sum++;
            }
        }
        
        int longitud = 240+sum;
        System.out.println("\n" +"___MAMA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!___" +longitud+ "\n");
        byte[] datos = new byte[longitud];
        //public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
        datos[0] = this.getOp();
        datos[1] = this.gethType();
        datos[2] = this.gethLen();
        datos[3] = this.gethOps();
        System.arraycopy(this.xId, 0, datos, 4, 4);
        System.arraycopy(this.secs, 0, datos, 8, 2);
        System.arraycopy(this.flags, 0, datos, 10, 2);
        System.arraycopy(this.ciAddr, 0, datos, 12, 4);
        System.arraycopy(this.yiAddr, 0, datos, 16, 4);
        System.arraycopy(this.siAddr, 0, datos, 20, 4);
        System.arraycopy(this.giAddr, 0, datos, 24, 4);
        System.arraycopy(this.chAddr, 0, datos, 28, 16);
        System.arraycopy(this.sName, 0, datos, 44, 64);
        System.arraycopy(this.file, 0, datos, 108, 128);
        System.arraycopy(this.magic_cookie, 0, datos, 236, 4);
        
        
        int cont = 240;
        for (ArrayList<Object> dato : listofList) 
        {
            if(
                    Byte.toUnsignedInt((byte) dato.get(0)) != 255 &&
                    Byte.toUnsignedInt((byte) dato.get(0)) != 53  &&
                    Byte.toUnsignedInt((byte) dato.get(0)) != 61  
              )
            {
                
                datos[cont]=(byte) dato.get(0);//cabecera
                System.out.println("\n" +"___aqui se muertra el tipo de mensaje___" +String.valueOf(datos[cont]) + "\n");
                cont++;
                datos[cont]=(byte) dato.get(1);//longitud
                System.out.println("\n" +"___longitud mensaje___" +String.valueOf(datos[cont]) + "\n");
                cont++;
                
                
                byte[] temporal_bytes = new byte[4];
                temporal_bytes =   (byte[]) dato.get(2) ;
                System.out.println("\n" +"___tamaño siguiente dato___" +temporal_bytes.length + "\n");
                
                datos[cont]=temporal_bytes[0];//datos
                cont++;
                datos[cont]=temporal_bytes[1];//datos
                cont++;
                datos[cont]=temporal_bytes[2];//datos
                cont++;
                datos[cont]=temporal_bytes[3];//datos
            }
//            
//            if( Byte.toUnsignedInt((byte) dato.get(0)) == 61)
//            {
//                datos[cont]=(byte) dato.get(0);//cabecera
//                cont++;
//                datos[cont]=(byte) dato.get(1);//longitud
//                cont++;
//                datos[cont]=(byte) dato.get(2);//tipo
//                cont++;
//                byte[] temporal_bytes = new byte[6];
//                temporal_bytes =   (byte[]) dato.get(3) ;
//                
//                datos[cont]=temporal_bytes[0];//datos
//                cont++;
//                datos[cont]=temporal_bytes[1];//datos
//                cont++;
//                datos[cont]=temporal_bytes[2];//datos
//                cont++;
//                datos[cont]=temporal_bytes[3];//datos
//                cont++;
//                datos[cont]=temporal_bytes[4];//datos
//                cont++;
//                datos[cont]=temporal_bytes[5];//datos
//            }
            
            
            if( Byte.toUnsignedInt((byte) dato.get(0)) == 53)
            {
                datos[cont]=(byte) dato.get(0);//cabecera
                cont++;
                datos[cont]=(byte) dato.get(1);//longitud
                cont++;
                datos[cont]=(byte) dato.get(2);//dato
            }
            if(Byte.toUnsignedInt((byte) dato.get(0)) == 255)
            {
                datos[cont]=(byte) dato.get(0);
            }
            cont++;
        }
        
        if (longitud < 312){
            Integer x;
            byte temporal;
            x = 0;
            temporal=x.byteValue();
            int dif=312-longitud-1;
            int conta=longitud;
            byte[] infop=new byte[312];
             System.arraycopy(datos, 0, infop, 0, longitud);
             for(int i=1; i < dif;i++){
                 infop[conta]=temporal;
                 conta++;
             }
             imprimir_areglo(infop);
             return infop;
        }
      
      
        
       
        imprimir_areglo(datos);
        return datos;
    }

    //faltaria la parte de la opciones OPCIONES AQUI
    //el tamañp es variable
    public Message() {
    }

    public void setPadding(byte[] padding) {
        this.padding = padding;
    }

    public void setOp(byte op) {
        this.op = op;
    }

    public void sethType(byte hType) {
        this.hType = hType;
    }

    public void sethLen(byte hLen) {
        this.hLen = hLen;
    }

    public void sethOps(byte hOps) {
        this.hOps = hOps;
    }

    public void setxId(byte[] xId) {
        this.xId = xId;
    }

    public void setxId(byte xId, int i) {
        this.xId[i] = xId;
    }

    public void setSecs(byte[] secs) {
        this.secs = secs;
    }

    public void setSecs(byte secs, int i) {
        this.secs[i] = secs;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
    }

    public void setFlags(byte flags, int i) {
        this.flags[i] = flags;
    }

    public void setCiAddr(byte[] ciAddr) {
        this.ciAddr = ciAddr;
    }

    public void setCiAddr(byte ciAddr, int i) {
        this.ciAddr[i] = ciAddr;
    }

    public void setYiAddr(byte[] yiAddr) {
        this.yiAddr = yiAddr;
    }

    public void setYiAddr(byte yiAddr, int i) {
        this.yiAddr[i] = yiAddr;
    }

    public void setSiAddr(byte[] siAddr) {
        this.siAddr = siAddr;
    }

    public void setSiAddr(byte siAddr, int i) {
        this.siAddr[i] = siAddr;
    }

    public void setGiAddr(byte[] giAddr) {
        this.giAddr = giAddr;
    }

    public void setGiAddr(byte giAddr, int i) {
        this.giAddr[i] = giAddr;
    }

    public void setChAddr(byte[] chAddr) {
        this.chAddr = chAddr;
    }

    public void setChAddr(byte chAddr, int i) {
        this.chAddr[i] = chAddr;
    }

    public void setsName(byte[] sName) {
        this.sName = sName;
    }

    public void setsName(byte sName, int i) {
        this.sName[i] = sName;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setFile(byte file, int i) {
        this.file[i] = file;
    }

    public void setMagic_cookie(byte[] magic_cookie) {
        this.magic_cookie = magic_cookie;
    }

    public void setMagic_cookie(byte magic_cookie, int i) {
        this.magic_cookie[i] = magic_cookie;
    }

    public byte[] getPadding() {
        return padding;
    }

    public byte getOp() {
        return op;
    }

    public byte gethType() {
        return hType;
    }

    public byte gethLen() {
        return hLen;
    }

    public byte gethOps() {
        return hOps;
    }

    public byte[] getxId() {
        return xId;
    }

    public byte[] getSecs() {
        return secs;
    }

    public byte[] getFlags() {
        return flags;
    }

    public byte[] getCiAddr() {
        return ciAddr;
    }

    public byte[] getYiAddr() {
        return yiAddr;
    }

    public byte[] getSiAddr() {
        return siAddr;
    }

    public byte[] getGiAddr() {
        return giAddr;
    }

    public byte[] getChAddr() {
        return chAddr;
    }

    public byte[] getsName() {
        return sName;
    }

    public byte[] getFile() {
        return file;
    }

    public byte[] getMagic_cookie() {
        return magic_cookie;
    }

    public DHCPOption getOpciones() {
        return opciones;
    }

    public void setOpciones(DHCPOption opciones) {
        this.opciones = opciones;
    }

    public void imprimir() {//
//      flags,ciAddr,yiAddr,
//              siAddr,giAddr,chAddr,sName,file ,magic_cookie;

//      op=String.format("%02X", this.op);
//      hType=String.format("%02X", this.hType);
//      hLen=String.format("%02X", this.hLen);
//      hOps=String.format("%02X", this.hOps);
//        for(int i=0 ; i<4 ; i++)
//        {
//            xId=xId+String.format("%02X", this.xId[i]); 
//        }
        System.out.println("\n Op:" + Byte.toUnsignedInt(op) + "\n");
        System.out.println("\n hType:" + Byte.toUnsignedInt(hType) + "\n");
        System.out.println("\n hLen:" + Byte.toUnsignedInt(hLen) + "\n");
        System.out.println("\n hOps:" + Byte.toUnsignedInt(hOps) + "\n");
        System.out.println("\n xId:" + Byte.toUnsignedInt(xId[0])
                + Byte.toUnsignedInt(xId[1])
                + Byte.toUnsignedInt(xId[2])
                + Byte.toUnsignedInt(xId[3]) + "\n");
        System.out.println("\n secs:" + Byte.toUnsignedInt(secs[0])
                + Byte.toUnsignedInt(secs[1]) + "\n");
        System.out.println("\n flags:" + Byte.toUnsignedInt(flags[0])
                + Byte.toUnsignedInt(flags[0]) + "\n");
        System.out.println("\n Ip Cliente:" + Byte.toUnsignedInt(ciAddr[0])
                + Byte.toUnsignedInt(ciAddr[1])
                + Byte.toUnsignedInt(ciAddr[2])
                + Byte.toUnsignedInt(ciAddr[3]) + "\n");
        System.out.println("\n Ip Your(Cliente):" + Byte.toUnsignedInt(yiAddr[0])
                + Byte.toUnsignedInt(yiAddr[1])
                + Byte.toUnsignedInt(yiAddr[2])
                + Byte.toUnsignedInt(yiAddr[3]) + "\n");
        System.out.println("\n Ip Server:" + Byte.toUnsignedInt(siAddr[0])
                + Byte.toUnsignedInt(siAddr[1])
                + Byte.toUnsignedInt(siAddr[2])
                + Byte.toUnsignedInt(siAddr[3]) + "\n");
        System.out.println("\n Ip Relay agente:" + Byte.toUnsignedInt(giAddr[0])
                + Byte.toUnsignedInt(giAddr[1])
                + Byte.toUnsignedInt(giAddr[2])
                + Byte.toUnsignedInt(giAddr[3]) + "\n");
        String Objeto1 = Integer.toHexString(Byte.toUnsignedInt(chAddr[0]));
        String Objeto2 = Integer.toHexString(Byte.toUnsignedInt(chAddr[1]));
        String Objeto3 = Integer.toHexString(Byte.toUnsignedInt(chAddr[2]));
        String Objeto4 = Integer.toHexString(Byte.toUnsignedInt(chAddr[3]));
        String Objeto5 = Integer.toHexString(Byte.toUnsignedInt(chAddr[4]));
        String Objeto6 = Integer.toHexString(Byte.toUnsignedInt(chAddr[5]));
        System.out.println("\n MAC Cliente:" + Objeto1 + "_" + Objeto2 + "_" + Objeto3 + "_" + Objeto4 + "_" + Objeto5 + "_" + Objeto6 + "\n");
        String nserver = new String(sName, StandardCharsets.UTF_8);
        System.out.println("\n Nombre del servidor:" + nserver + "\n");
        String nfile = new String(file, StandardCharsets.UTF_8);
        System.out.println("\n Nombre del archivo:" + nfile + "\n");
        Objeto1 = Integer.toHexString(Byte.toUnsignedInt(magic_cookie[0]));
        Objeto2 = Integer.toHexString(Byte.toUnsignedInt(magic_cookie[1]));
        Objeto3 = Integer.toHexString(Byte.toUnsignedInt(magic_cookie[2]));
        Objeto4 = Integer.toHexString(Byte.toUnsignedInt(magic_cookie[3]));
        System.out.println("\n Magic Cookie:" + Objeto1 + Objeto2 + Objeto3 + Objeto4 + "\n");

        //System.out.println("\n Tipo Mensaje:" +Byte.toUnsignedInt(TypoMen)+"\n");
    }

    private void imprimir_areglo(byte[] datos) {
        
        System.out.println("\n Imprime el mensaje que creamos \n");
        System.out.println("\n Op:" + Byte.toUnsignedInt(datos[0]) + "\n");
        System.out.println("\n hType:" + Byte.toUnsignedInt(datos[1]) + "\n");
        System.out.println("\n hLen:" + Byte.toUnsignedInt(datos[2]) + "\n");
        System.out.println("\n hOps:" + Byte.toUnsignedInt(datos[3]) + "\n");
        System.out.println("\n xId:" + Byte.toUnsignedInt(datos[4])
                + Byte.toUnsignedInt(datos[5])
                + Byte.toUnsignedInt(datos[6])
                + Byte.toUnsignedInt(datos[7]) + "\n");
        System.out.println("\n secs:" + Byte.toUnsignedInt(datos[8])
                + Byte.toUnsignedInt(datos[9]) + "\n");
        System.out.println("\n flags:" + Byte.toUnsignedInt(datos[10])
                + Byte.toUnsignedInt(datos[11]) + "\n");
        System.out.println("\n ciAddr:" + Byte.toUnsignedInt(datos[12])
                + Byte.toUnsignedInt(datos[13])
                + Byte.toUnsignedInt(datos[14])
                + Byte.toUnsignedInt(datos[15]) + "\n");
        System.out.println("\n yiAddr:" + Byte.toUnsignedInt(datos[16])
                + Byte.toUnsignedInt(datos[17])
                + Byte.toUnsignedInt(datos[18])
                + Byte.toUnsignedInt(datos[19]) + "\n");
        System.out.println("\n siAddr:" + Byte.toUnsignedInt(datos[20])
                + Byte.toUnsignedInt(datos[21])
                + Byte.toUnsignedInt(datos[22])
                + Byte.toUnsignedInt(datos[23]) + "\n");
        System.out.println("\n giAddr:" + Byte.toUnsignedInt(datos[24])
                + Byte.toUnsignedInt(datos[25])
                + Byte.toUnsignedInt(datos[26])
                + Byte.toUnsignedInt(datos[27]) + "\n");
        
        String Objeto1 = Integer.toHexString(Byte.toUnsignedInt(datos[28]));
        String Objeto2 = Integer.toHexString(Byte.toUnsignedInt(datos[29]));
        String Objeto3 = Integer.toHexString(Byte.toUnsignedInt(datos[30]));
        String Objeto4 = Integer.toHexString(Byte.toUnsignedInt(datos[31]));
        String Objeto5 = Integer.toHexString(Byte.toUnsignedInt(datos[32]));
        String Objeto6 = Integer.toHexString(Byte.toUnsignedInt(datos[33]));
        System.out.println("\n chAddr:" + Objeto1 + "_" + Objeto2 + "_" + Objeto3 + "_" + Objeto4 + "_" + Objeto5 + "_" + Objeto6 + "\n");
        
        System.out.println("padding chAddr" + Byte.toUnsignedInt(datos[34])  + Byte.toUnsignedInt(datos[35]) 
                + Byte.toUnsignedInt(datos[36]) + Byte.toUnsignedInt(datos[37]) + Byte.toUnsignedInt(datos[38]) + Byte.toUnsignedInt(datos[39]) 
                + Byte.toUnsignedInt(datos[40]) + Byte.toUnsignedInt(datos[41]) + Byte.toUnsignedInt(datos[42]) + Byte.toUnsignedInt(datos[43]) + "\n");

        for (int i = 44; i < 108; i++) {
            System.out.println(Byte.toUnsignedInt(datos[i]));
        }
        System.out.println("\n");

        for (int i = 108; i < 236; i++) {
            System.out.println(Byte.toUnsignedInt(datos[i]));
        }
        System.out.println("\n");
        System.out.println("\n Magic Cookie:" +Byte.toUnsignedInt(datos[236]) + 
                                     Byte.toUnsignedInt(datos[237]) + 
                                     Byte.toUnsignedInt(datos[238]) + 
                                     Byte.toUnsignedInt(datos[239]) +"\n");
        
        
        System.out.println("\n OPCIONES:" +"\n");
        for (int i = 240; i < datos.length; i++) 
        {
            System.out.println("\n___" +Byte.toUnsignedInt(datos[i]) +"___\n");
        }
        System.out.println("\n longitud total:___" +datos.length +"___\n");
    }
}
