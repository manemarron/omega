/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author manemarron
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    @XmlElement
    private String name;
    @XmlElement
    private String type;
    @XmlElement
    private int size;
    @XmlElement
    private boolean nullable;
    @XmlElement
    private boolean pk;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public boolean isNullable() {
        return nullable;
    }
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPk() {
        return pk;
    }
    public void setPk(boolean pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "Column{" + "name=" + name + ", type=" + type + ", size=" + size + ", nullable=" + nullable + ", pk=" + pk + '}';
    }
    
    
}
