/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;
/**
 *
 * @author JOHN_
 */
public class Registro_data_ip { //  Usar Lista para saber la IPs asignadas, y la fecha de finalizacion y la mac utilizada
   String IP; //Direccion en decimal
   String MAC; //En Hexadecimal
   String MSub; //En decimal
   String PEnlace; //En decimal
   String DNS; //En decimal
   String  TPrestamo; //Entero que representa los segundos
   String FPrestamo; // Fecha y hora en la que se hizo el Registro_data_ip 
   String FExpiracion; //Fecha y hora en la que se termina la concesion 

    public Registro_data_ip() {
    }

    
    public Registro_data_ip(String IP, String MAC, String MSub, String PEnlace, String DNS, String TPrestamo, String FPrestamo, String FExpiracion) {
        this.IP = IP;
        this.MAC = MAC;
        this.MSub = MSub;
        this.PEnlace = PEnlace;
        this.DNS = DNS;
        this.TPrestamo = TPrestamo;
        this.FPrestamo = FPrestamo;
        this.FExpiracion = FExpiracion;
    }
      
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getMSub() {
        return MSub;
    }

    public void setMSub(String MSub) {
        this.MSub = MSub;
    }

    public String getPEnlace() {
        return PEnlace;
    }

    public void setPEnlace(String PEnlace) {
        this.PEnlace = PEnlace;
    }

    public String getDNS() {
        return DNS;
    }

    public void setDNS(String DNS) {
        this.DNS = DNS;
    }

    public String getTPrestamo() {
        return TPrestamo;
    }

    public void setTPrestamo(String TPrestamo) {
        this.TPrestamo = TPrestamo;
    }
    
    public String getFPrestamo() {
        return FPrestamo;
    }

    public void setFPrestamo(String FPrestamo) {
        this.FPrestamo = FPrestamo;
    }
    
    public String getFExpiracion() {
        return FExpiracion;
    }

    public void setFExpiracion(String FExpiracion) {
        this.FExpiracion = FExpiracion;
    }


}
