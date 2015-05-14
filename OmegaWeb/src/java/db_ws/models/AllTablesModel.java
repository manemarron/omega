/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manemarron
 */
@XmlRootElement(name = "AllTablesModel")
public class AllTablesModel {
    private ArrayList<String> tables;

    public ArrayList<String> getTables() {
        return tables;
    }

    @XmlElement
    @XmlElementWrapper(name = "tables")
    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }
    
    
}
