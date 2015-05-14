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
    private int user_id;

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

    public int getUser_id() {
        return user_id;
    }

    @XmlElement
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
