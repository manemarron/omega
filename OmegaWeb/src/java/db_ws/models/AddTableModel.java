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
    private String table_name;
    private ArrayList<Column> columns;

    public String getTable_name() {
        return table_name;
    }

    @XmlElement
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    
    public ArrayList<Column> getColumns() {
        return columns;
    }

    @XmlElement
    @XmlElementWrapper(name="columns")
    public void setColumns(ArrayList<Column> columns) {
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
}