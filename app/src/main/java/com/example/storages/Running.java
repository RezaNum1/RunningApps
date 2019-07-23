package com.example.storages;

import java.io.Serializable;

public class Running implements Serializable {
    private String position;
    private String destination;
    private String jarak;
    private String waktutempuh;
    private String tanggal;
    private String key;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getWaktutempuh() {
        return waktutempuh;
    }

    public void setWaktutempuh(String waktutempuh) {
        this.waktutempuh = waktutempuh;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Running(){

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return" "+position+"\n" +
                " "+destination +"\n" +
                " "+jarak +"\n" +
                " "+waktutempuh +"\n" +
                " "+tanggal;
    }

    public Running(String pos, String des, String jar, String wak, String tan){
        position = pos;
        destination = des;
        jarak = jar;
        waktutempuh = wak;
        tanggal = tan;
    }
}
