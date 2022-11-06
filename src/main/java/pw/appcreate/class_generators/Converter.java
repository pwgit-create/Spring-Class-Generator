package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

/**
 * Created by Peter on 2020-05-23.
 */
public class Converter extends BaseClassGenerator {

    private boolean isConverterImplementation;

    private final String NEW = "new";
    private final String NULLABLE = "Optional.ofNullable(";
    private final String VALUE = "value.";
    private final String RECORD = "record.";
    private final String ELSENULL = ".orElse(null);";


    public Converter(boolean isConverterImplementation) {

        this.isConverterImplementation = isConverterImplementation;
    }


    @Override
    public String generateTextForClass(DomainStructure domainStructure) {

        classText = "@Component" + n;


        classText = classText.concat(PUBLIC_ClASS + s + domainStructure.getClassName() + MODEL + CONVERTER + s + IMPLEMENTS + s + "Crud" + CONVERTER + "<" +
                domainStructure.getClassName() + "," + domainStructure.getClassName() + MODEL + ">");

        classText = classText.concat(sB);

        classText = classText.concat(getConvertMethod(domainStructure));
        classText = classText.concat(getconvertModelForInsert(domainStructure));
        classText = classText.concat(getConvertModelForUpdate(domainStructure));

        classText = classText.concat(eB);


        return classText;
    }


    private String addAttributesToConvertMethod(DomainStructure domainStructure) {


        String implementationString = domainStructure.getClassName() + MODEL + s + MODEL.toLowerCase() + s +
                "=" + s + NEW + s + domainStructure.getClassName() + MODEL + EE + n;


        for (Attribute attribute : domainStructure.getAttributes()) {


            implementationString = implementationString.concat(MODEL.toLowerCase() + "." + attribute.getJavaName() +
                    s + "=" + s + NULLABLE + VALUE + attribute.getJavaName() + ");" + n);


        }

        implementationString = implementationString.concat(n + RETURN + s + MODEL.toLowerCase() + ";");


        return implementationString;

    }

    private String getConvertMethod(DomainStructure domainStructure) {


        String convertMethod = n + OVERRIDE + n + domainStructure.getClassName() + MODEL + s + "convert(" + domainStructure.getClassName() + s + "value)" +
                sB + n;

        if (isConverterImplementation) {

            convertMethod = convertMethod.concat(addAttributesToConvertMethod(domainStructure));


            convertMethod = convertMethod.concat(n + eB);
        } else {

            convertMethod = convertMethod.concat("// Put attribute content here" + n + eB);
        }


        return convertMethod;

    }

    private String getconvertModelForInsert(DomainStructure domainStructure) {


        String convertModelForInsertMethod = n + OVERRIDE + n + domainStructure.getClassName() + s + "convertModelForInsert(" +
                domainStructure.getClassName() + MODEL +s+MODEL.toLowerCase()+ ")" + sB + n;


        if (isConverterImplementation) {

            convertModelForInsertMethod = convertModelForInsertMethod.concat(
                    addAttributesToInsertMethod(domainStructure));


            convertModelForInsertMethod = convertModelForInsertMethod.concat(n + eB);

        } else {

            convertModelForInsertMethod = convertModelForInsertMethod.concat(n + "// Put attribute content here" + n + eB);
        }

        return convertModelForInsertMethod;
    }


    private String addAttributesToInsertMethod(DomainStructure domainStructure) {


        String implementationString = domainStructure.getClassName() + s + "record" + s + "=" + s +
                NEW + s + domainStructure.getClassName() + EE + n;


        for (Attribute attribute : domainStructure.getAttributes()) {


            if (!attribute.isPrimaryKey()) {

                implementationString = implementationString.concat(RECORD + attribute.getJavaName() + s + "=" + s + MODEL.toLowerCase() + "." +
                        attribute.getJavaName() + ELSENULL + n);

            }


        }

        implementationString = implementationString.concat(RETURN + s + "record;");


        return implementationString;

    }

    private String addAttributesToUpdateMethod(DomainStructure domainStructure) {


        String implementationString = domainStructure.getClassName() + s + "record" + s + "=" + s +
                NEW + s + domainStructure.getClassName() + EE + n;


        for (Attribute attribute : domainStructure.getAttributes()) {


            implementationString = implementationString.concat(RECORD + attribute.getJavaName() + s + "=" + s + MODEL.toLowerCase() + "." +
                    attribute.getJavaName() + ELSENULL + n);


        }

        implementationString = implementationString.concat(RETURN + s + "record;");


        return implementationString;

    }


    private String getConvertModelForUpdate(DomainStructure domainStructure) {


        String convertModelForUpdateMethod = n + OVERRIDE + n + domainStructure.getClassName() + s + "convertModelForUpdate(" +
                domainStructure.getClassName() + MODEL +s+MODEL.toLowerCase()+ ")" +
                sB + n;


        if (isConverterImplementation) {

            convertModelForUpdateMethod = convertModelForUpdateMethod.concat(addAttributesToUpdateMethod(domainStructure));


            convertModelForUpdateMethod = convertModelForUpdateMethod.concat(n + eB);
        } else {

            convertModelForUpdateMethod = convertModelForUpdateMethod.concat("// Put attribute content here" + n + eB);
        }


        return convertModelForUpdateMethod;
    }

    @Override
    public String getClassFileName(DomainStructure domainStructure) {

        return domainStructure.getClassName() + MODEL + CONVERTER + DOTJAVA;
    }


}
