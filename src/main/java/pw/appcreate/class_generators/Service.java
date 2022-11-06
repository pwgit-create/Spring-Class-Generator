package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

/**
 * Created by Peter on 2020-05-23.
 */
public class Service extends BaseInterfaceGenerator {


    private final String SERVICE = "Service";
    private final String MONO = "Mono<";
    private final String FLUX = "Flux<";

    private final String IMPORT_MONO = "import reactor.core.publisher.Mono;";
    private final String IMPORT_FLUX = "import reactor.core.publisher.Flux;";

    public Service(boolean isBasicCrudOperations) {

        this.isBasicCrudOperations = isBasicCrudOperations;
    }


    @Override
    public String generateTextForClass(DomainStructure domainStructure) {


        if (isBasicCrudOperations) {

            //TODO: Add imports for Autowired and Service

            classText = IMPORT_MONO + n + IMPORT_FLUX + n + n;

        } else {
            classText = "";
        }

        classText = classText.concat("@" + SERVICE + n);

        classText = classText.concat(PUBLIC_ClASS + s + domainStructure.getClassName() + SERVICE + IMPL + s +
                EXTENDS + s + "BaseCrud" + SERVICE + "<" + domainStructure.getClassName() + MODEL + "," + domainStructure.getClassName() + "," +
                domainStructure.getClassName() + MODEL + CONVERTER + "," + domainStructure.getClassName() + REPOSITORY + ">" +
                n + IMPLEMENTS + s + domainStructure.getClassName() + SERVICE);

        classText = classText.concat(sB);

        classText = classText.concat(getConstructor(domainStructure));

        if (isBasicCrudOperations) {

            /**
             * Must add data to basicCRUDClassImplementation variable before concat
             */
            generateBasicCrudImplementationForClass(domainStructure);

            classText = classText.concat(n + basicCRUDImplementationClass);
        }

        classText = classText.concat(n+n+eB);

        return classText;
    }

    private String getConstructor(DomainStructure domainStructure) {

        return AUTOWIRED + n + PUBLIC + s + domainStructure.getClassName() + SERVICE + IMPL + "(" + domainStructure.getClassName() +
                REPOSITORY + s + REPOSITORY.toLowerCase() + "," + n +
                domainStructure.getClassName() + MODEL + CONVERTER + s + CONVERTER.toLowerCase() + ") {" + n +
                s + n + SUPER + "(" + REPOSITORY.toLowerCase() + "," + CONVERTER.toLowerCase() + ")" + s + s + "}";


    }

    @Override
    public String getClassFileName(DomainStructure domainStructure) {
        return domainStructure.getClassName() + SERVICE + IMPL + DOTJAVA;
    }


    @Override
    public String generateTextForInterface(DomainStructure domainStructure) {

        if (isBasicCrudOperations) {

            classText = IMPORT_MONO + n + IMPORT_FLUX + n + n;

        } else {
            classText = "";
        }

        classText = classText.concat(PUBLIC + s + INTERFACE + s + domainStructure.getClassName() + SERVICE + s + EXTENDS + s + "Crud" + SERVICE + "<" +
                domainStructure.getClassName() + MODEL + ">");

        classText = classText.concat(sB);

        if (isBasicCrudOperations) {

            /**
             * Must add data to basicCRUDInterfaceDeceleration variable before concat
             */
            generateBasicCRUDInterfaceDeceleration(domainStructure);

            classText = classText.concat(basicCRUDInterfaceDeceleration);

        } else {
            classText = classText.concat("// Declare the Interface here..");
        }


        classText = classText.concat(eB);

        return classText;
    }

    private void generateBasicCRUDInterfaceDeceleration(DomainStructure domainStructure) {

        Attribute primaryKeyAttribute = findPrimaryKeyAttribute(domainStructure.getAttributes());


        basicCRUDInterfaceDeceleration = MONO + domainStructure.getClassName() + MODEL + ">" + s + BYID +
                primaryKeyAttribute.getDataType() + s + primaryKeyAttribute.getJavaName() + EER + n + n;

        basicCRUDInterfaceDeceleration = basicCRUDInterfaceDeceleration.concat(FLUX + domainStructure.getClassName()
                + MODEL + ">" +
                s + GETALL + ";");
    }

    private void generateBasicCrudImplementationForClass(DomainStructure domainStructure) {

        Attribute primaryKeyAttribute = findPrimaryKeyAttribute(domainStructure.getAttributes());


        basicCRUDImplementationClass = n + OVERRIDE + n + PUBLIC + s + MONO + domainStructure.getClassName() + MODEL +
                ">" + s + BYID + primaryKeyAttribute.getDataType() + s + primaryKeyAttribute.getJavaName() + ")" + s + s +
                "{" + s + s + RETURN + s + "publishSingle(" + REPOSITORY.toLowerCase() + "." + BYID + primaryKeyAttribute.getJavaName() +
                ")," + s + CONVERTER.toLowerCase() + EER + s + s + "}" + n + n;


        basicCRUDImplementationClass = basicCRUDImplementationClass.concat(OVERRIDE + n + PUBLIC + s + FLUX
                + domainStructure.getClassName() + MODEL + ">" + s + GETALL + s + s + "{" + s + s + RETURN + s + "publishStream(" +
                REPOSITORY.toLowerCase() + "." + GETALL + ".stream()," + s + CONVERTER.toLowerCase() + EER + s + s + "}");
    }

    @Override
    public String getInterfaceFileName(DomainStructure domainStructure) {
        return domainStructure.getClassName() + SERVICE + DOTJAVA;
    }
}
