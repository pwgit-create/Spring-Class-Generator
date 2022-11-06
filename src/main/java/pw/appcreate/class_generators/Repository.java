package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

/**
 * Created by Peter on 2020-05-23.
 */
public class Repository extends BaseInterfaceGenerator {

    private final String EXPRESSION = "expression()";
    private final String s6= s+s+s+s+s+s;

    public Repository(boolean isBasicCrudOperations) {

        this.isBasicCrudOperations = isBasicCrudOperations;

    }


    @Override
    public String generateTextForClass(DomainStructure domainStructure) {


        if (isBasicCrudOperations) {

            //TODO: Add imports for Autowired and Repository
            classText = IMPORT_LIST + n + IMPORT_OPTIONAL + n + n;

        } else {
            classText = "";
        }


        classText = classText.concat("@" + REPOSITORY + n);

        classText = classText.concat(PUBLIC_ClASS + s + domainStructure.getClassName() + REPOSITORY + IMPL + s + EXTENDS + s + "BaseJdbcRepository<" +
                domainStructure.getClassName() + ">" + s + IMPLEMENTS + s + domainStructure.getClassName() + REPOSITORY);

        classText = classText.concat(sB);

        classText = classText.concat(getConstructor(domainStructure));

        if (isBasicCrudOperations) {

            /**
             * Must add data to basicCRUDClassImplementation variable before concat
             */
            generateBasicCrudImplementationForClass(domainStructure);

            classText = classText.concat(n+basicCRUDImplementationClass);}

            classText = classText.concat(eB);

        return classText;
    }


    private String getConstructor(DomainStructure domainStructure) {

        return AUTOWIRED + n + PUBLIC + s + domainStructure.getClassName() + REPOSITORY + IMPL + "(){" + s + SUPER + "(" +
                domainStructure.getClassName() + ".class); }";
    }


    @Override
    public String generateTextForInterface(DomainStructure domainStructure) {


        if (isBasicCrudOperations) {
            classText = IMPORT_LIST + n + IMPORT_OPTIONAL + n + n;

        } else {
            classText = "";
        }

        classText = classText.concat(PUBLIC + s + INTERFACE + s + domainStructure.getClassName()
                + REPOSITORY + s + EXTENDS + s + "JdbcRepository<" +
                domainStructure.getClassName() + ">");

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

    private void generateBasicCrudImplementationForClass(DomainStructure domainStructure) {

          Attribute primaryKeyAttribute = findPrimaryKeyAttribute(domainStructure.getAttributes());


        basicCRUDImplementationClass = n+ OVERRIDE +n+PUBLIC+s+ OPTIONAL +"<"+domainStructure.getClassName()+">"+s+
                BYID+ primaryKeyAttribute.getDataType()+s+primaryKeyAttribute.getJavaName()+")"+s+"{"+n+RETURN+s+
                EXPRESSION+n+s6+".where (e -> e."+primaryKeyAttribute.getJavaName()+","+s+
                primaryKeyAttribute.getJavaName()+
                ")"+n+s6+".getOne"+EE+n+"}";

        basicCRUDImplementationClass = basicCRUDImplementationClass.concat(n+n+ OVERRIDE +n+
        LIST+domainStructure.getClassName()+">"+s+GETALL+s+"{"+n+RETURN+s+EXPRESSION+n+s6+".getList"+EE+n+s+"}");


    }

    private void generateBasicCRUDInterfaceDeceleration(DomainStructure domainStructure) {

        Attribute primaryKeyAttribute = findPrimaryKeyAttribute(domainStructure.getAttributes());


        basicCRUDInterfaceDeceleration = OPTIONAL + "<" + domainStructure.getClassName() + ">" +
                s + BYID + primaryKeyAttribute.getDataType() + s + primaryKeyAttribute.getJavaName() + EER + n + n;


        basicCRUDInterfaceDeceleration = basicCRUDInterfaceDeceleration.concat(LIST + domainStructure.getClassName() +
                s + GETALL+";");


    }

    @Override
    public String getClassFileName(DomainStructure domainStructure) {
        return domainStructure.getClassName() + REPOSITORY + IMPL + DOTJAVA;
    }

    @Override
    public String getInterfaceFileName(DomainStructure domainStructure) {
        return domainStructure.getClassName() + REPOSITORY + DOTJAVA;
    }


}
