/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.utils;

import java.util.HashMap;
import sn.iso4.iso8583.type.ConnexionStatus;
import sn.iso4.iso8583.type.Session;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class SessionList {

    public static HashMap<String, Session> sessions;

    public static void addSession(String id, Session session) {
        if (sessions == null) {
            sessions = new HashMap<>();
        }
        sessions.put(id, session);
    }

    public static void removeSession(String id) {
        sessions.remove(id);
    }

    public static void updateSession(String id, ConnexionStatus status) {
        sessions.get(id).setConnexionStatus(status);
    }
    
    public static Session getSession(String id) {
        return sessions.get(id);
    }

}
