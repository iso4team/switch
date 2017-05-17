/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.iso4.iso8583.type;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class Session {
    
    private String clientIP;
    private int clientPort;
    
    private String serverIP;
    private int serverPort;
    
    private ConnexionStatus connexionStatus;
    private ConnexionType connexionType;

    public Session() {
    }

    public Session(String clientIP, int clientPort, String serverIP, int serverPort, ConnexionStatus connexionStatus, ConnexionType connexionType) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.connexionStatus = connexionStatus;
        this.connexionType = connexionType;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public ConnexionStatus getConnexionStatus() {
        return connexionStatus;
    }

    public void setConnexionStatus(ConnexionStatus connexionStatus) {
        this.connexionStatus = connexionStatus;
    }

    public ConnexionType getConnexionType() {
        return connexionType;
    }

    public void setConnexionType(ConnexionType connexionType) {
        this.connexionType = connexionType;
    }
    
}
