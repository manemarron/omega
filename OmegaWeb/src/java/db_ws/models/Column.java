/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author manemarron
 */
@XmlType(propOrder = {"name","type","size","nullable","pk"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    private String name;
    private String type;
    private int size;
    private boolean nullable;
    private boolean pk;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    @XmlElement
    public void setSize(int size) {
        this.size = size;
    }

    public boolean isNullable() {
        return nullable;
    }

    @XmlElement
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPk() {
        return pk;
    }

    @XmlElement
    public void setPk(boolean pk) {
        this.pk = pk;
    }
    
    
}
