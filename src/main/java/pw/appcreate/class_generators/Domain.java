package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

import java.util.List;

/**
 * Created by Peter on 2020-05-23.
 */
public class Domain extends BaseClassGenerator {


    public Domain() {
    }

    @Override
    public String generateTextForClass(DomainStructure domainStructure) {


        classText = PUBLIC_ClASS + s + domainStructure.getClassName() + s + EXTENDS+s+"BaseRecord";

        classText = classText.concat(sB);

        addAttributesToClassText(domainStructure.getAttributes());

        classText = classText.concat(getTableNameMethodText(domainStructure.getTableName()));


        classText = classText.concat(eB);


        return classText;
    }



    private void addAttributesToClassText(List<Attribute> attributes) {

        String attributeStringJava = "";

        for (Attribute attribute : attributes) {


            attributeStringJava = attribute.getAccessModifier().name().toLowerCase() + s + attribute.getDataType() + s
                    + attribute.getJavaName() + ";";


            classText = classText.concat(n + getMappedField(attribute) + n + attributeStringJava + n);

        }


    }

    private String getTableNameMethodText(String tableName) {


        return n + OVERRIDE + n + "public String getTableName(){" + n + "return " +"\""+ tableName + "\"" +";" + n +
                "}";

    }


    private String getMappedField(Attribute attribute) {


        if (attribute.isPrimaryKey()) {

            return "@Mappedfield(name =\"" + attribute.getDomainName()
                    + "\",primaryKey = true)";

        } else {
            return "@Mappedfield(name =\"" + attribute.getDomainName()
                    + "\")";

        }
    }

    @Override
    public String getClassFileName(DomainStructure domainStructure) {

        return domainStructure.getClassName()+DOTJAVA;
    }



}
