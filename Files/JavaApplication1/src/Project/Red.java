/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.ArrayList;

/**
 *
 * @author pruebas
 */
public class Red {

    
    private  ArrayList<Object> rango_direcciones_subred ;
    private  ArrayList<Object> rango_direcciones_a_entregar  ;
    private  byte[] mascara_red =  new byte [4] ;
    private  byte[] gateway  =new byte [4] ;
    private  byte[] dns  = new byte [4] ;
    private  int lease_time;

    public Red() {
    }    

    public ArrayList<Object> getRango_direcciones_subred() {
        return rango_direcciones_subred;
    }

    public void setRango_direcciones_subred(ArrayList<Object> rango_direcciones_subred) {
        this.rango_direcciones_subred = rango_direcciones_subred;
    }

    public ArrayList<Object> getRango_direcciones_a_entregar() {
        return rango_direcciones_a_entregar;
    }

    public void setRango_direcciones_a_entregar(ArrayList<Object> rango_direcciones_a_entregar) {
        this.rango_direcciones_a_entregar = rango_direcciones_a_entregar;
    }

    public byte[] getMascara_red() {
        return mascara_red;
    }

    public void setMascara_red(byte[] mascara_red) {
        this.mascara_red = mascara_red;
    }

    public byte[] getGateway() {
        return gateway;
    }

    public void setGateway(byte[] gateway) {
        this.gateway = gateway;
    }

    public byte[] getDns() {
        return dns;
    }

    public void setDns(byte[] dns) {
        this.dns = dns;
    }

    public int getLease_time() {
        return lease_time;
    }

    public void setLease_time(int lease_time) {
        this.lease_time = lease_time;
    }
    


}
