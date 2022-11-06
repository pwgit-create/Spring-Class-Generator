package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

import java.util.List;

/**
 * Created by Peter on 2020-05-23.
 */
public class Model extends BaseClassGenerator {


    private String OPTIONAL_DOT_EMPTY = "Optional.empty()";

    public Model() {
    }


    @Override
    public String generateTextForClass(DomainStructure domainStructure) {

        classText = IMPORT_OPTIONAL +n+n;

        classText = classText.concat(PUBLIC_ClASS + s + domainStructure.getClassName() + MODEL);

        classText = classText.concat(sB);


        addAttributesToClassText(domainStructure.getAttributes());

        classText = classText.concat(eB);


        return classText;
    }


    private void addAttributesToClassText(List<Attribute> attributes) {

        String attributeStringJava = "";

        for (Attribute attribute : attributes) {


            attributeStringJava = attribute.getAccessModifier().name().toLowerCase() + s + OPTIONAL + "<" +
                    attribute.getDataType() + ">" + s
                    + attribute.getJavaName() + s + "=" + s + OPTIONAL_DOT_EMPTY + ";";


            classText = classText.concat(n + attributeStringJava + n);

        }

    }

    @Override
    public String getClassFileName(DomainStructure domainStructure) {
        return domainStructure.getClassName() + MODEL+DOTJAVA;
    }
}
