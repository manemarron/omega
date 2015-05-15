/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author manemarron
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SelectColumns {
    private List<String> columns;

    public List<String> getColumns() {
        return columns;
    }

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
    
    
}
