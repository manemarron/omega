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
@XmlRootElement(name = "AddTableModel")
public class AddTableModel {
    private ArrayList<Column> columns;

    public ArrayList<Column> getColumns() {
        return columns;
    }

    @XmlElement
    @XmlElementWrapper(name="columns")
    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
}