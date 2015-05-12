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
@XmlRootElement(name = "DeleteDBModel")
public class DeleteDBModel {
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    @XmlElement
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
