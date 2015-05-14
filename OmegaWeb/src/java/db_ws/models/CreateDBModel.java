/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manemarron
 */
@XmlRootElement(name = "CreateDBModel")
public class CreateDBModel {
    private String dbName;
    private String user;
    private String pw;

    public String getDbName() {
        return dbName;
    }

    @XmlElement
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUser() {
        return user;
    }

    @XmlElement
    public void setUser(String user) {
        this.user = user;
    }

    public String getPw() {
        return pw;
    }

    @XmlElement
    public void setPw(String pw) {
        this.pw = pw;
    }
    
}
