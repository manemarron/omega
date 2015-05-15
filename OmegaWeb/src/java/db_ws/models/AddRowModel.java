/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manemarron
 */
@XmlRootElement(name="AddRowModel")
public class AddRowModel {
    String table_name;
    List<String> values;

    public List<String> getValues() {
        return values;
    }

    public String getTable_name() {
        return table_name;
    }

    @XmlElement(name="table_name")
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    
    

    @XmlElementWrapper(name="values")
    @XmlElement(name="value")
    public void setValues(List<String> values) {
        this.values = values;
    }
    
}
