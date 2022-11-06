package pw.appcreate.model;


import pw.appcreate.Enum.AccessModifier;

/**
 * Created by Peter on 2020-05-23.
 */
public class Attribute {

    private String javaName;

    private String domainName;

    private String dataType;

    private AccessModifier accessModifier;

    private boolean isPrimaryKey;

    public Attribute(AccessModifier accessModifier, String dataType, String javaVarName, String domainName, boolean isPrimaryKey) {
        this.javaName = javaVarName;
        this.domainName = domainName;
        this.dataType = dataType;
        this.accessModifier = accessModifier;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getDomainName() {
        return domainName;
    }


    public String getJavaName() {
        return javaName;
    }


    public String getDataType() {
        return dataType;
    }


    public AccessModifier getAccessModifier() {
        return accessModifier;
    }


    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }


}