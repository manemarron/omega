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
@XmlRootElement(name = "SelectRequestModel")
public class SelectRequestModel {

    private String tableName;
    private List<String> selectColumnNames;
    private List<String> whereColumnNames;
    private List<String> values;

    public String getTableName() {
        return tableName;
    }

    @XmlElement(name = "tableName")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getSelectColumnNames() {
        return selectColumnNames;
    }

    @XmlElementWrapper(name = "selectColumnNames")
    public void setSelectColumnNames(List<String> selectColumnNames) {
        this.selectColumnNames = selectColumnNames;
    }

    public List<String> getWhereColumnNames() {
        return whereColumnNames;
    }

    @XmlElementWrapper(name = "whereColumnNames")
    public void setWhereColumnNames(List<String> whereColumnNames) {
        this.whereColumnNames = whereColumnNames;
    }

    public List<String> getValues() {
        return values;
    }

    @XmlElementWrapper(name = "values")
    public void setValues(List<String> values) {
        this.values = values;
    }

}
