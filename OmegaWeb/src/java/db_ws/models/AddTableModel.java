/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manemarron
 */
@XmlRootElement(name = "AddTableModel")
public class AddTableModel {
    private String table_name;
    private List<Column> columns;

    public String getTable_name() {
        return table_name;
    }
    @XmlElement
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    
    public List<Column> getColumns() {
        return columns;
    }
    @XmlElementWrapper(name="columns")
    @XmlElement(name="column")
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
    
    public ArrayList<String> getColumnNames(){
        ArrayList<String> names = new ArrayList();
        for(int i=0;i<columns.size();i++){
            names.add(columns.get(i).getName());
        }
        return names;
    }
    
    public ArrayList<String> getColumnTypes(){
        ArrayList<String> types = new ArrayList();
        for(int i=0;i<columns.size();i++){
            String type = columns.get(i).getType();
            if(type.contains("char")){
                type += String.format("(%d)", columns.get(i).getSize());
            }
            types.add(type);
        }
        return types;
    }
    
    public ArrayList<String> getNulls(){
        ArrayList<String> nulls = new ArrayList();
        for(int i=0;i<columns.size();i++){
            nulls.add(columns.get(i).isNullable()?"NULL":"NOT NULL");
        }
        return nulls;
    }
    
    public ArrayList<String> getPks(){
        ArrayList<String> pks = new ArrayList();
        for(int i=0;i<columns.size();i++){
            Column column = columns.get(i);
            if(column.isPk())
                pks.add(column.getName());
        }
        return pks;
    }

    @Override
    public String toString() {
        String s = "AddTableModel{" + "table_name=" + table_name + ",columns=";
        String aux = "";
        for(Column c:columns){
            aux+=String.format("{%s},", c.toString());
        }
        s+=String.format("[%s]", aux);
        return s;
    }
    
}