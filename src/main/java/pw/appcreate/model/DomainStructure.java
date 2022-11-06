package pw.appcreate.model;

import java.util.List;

/**
 * Created by Peter on 2020-05-23.
 */
public class DomainStructure {

    private String className;
    private String tableName;
    private List<Attribute> attributes;

    public DomainStructure(String className, String tableName, List<Attribute> attributes) {
        this.className = className;
        this.tableName = tableName;
        this.attributes = attributes;
    }

    public String getClassName() {
        return className;
    }


    public String getTableName() {
        return tableName;
    }


    public List<Attribute> getAttributes() {
        return attributes;
    }


}
