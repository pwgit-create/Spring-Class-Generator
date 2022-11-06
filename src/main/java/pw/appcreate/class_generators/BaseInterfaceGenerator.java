package pw.appcreate.class_generators;


import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;

import java.util.List;

/**
 * Created by Peter on 2020-05-23.
 */
public abstract class BaseInterfaceGenerator extends BaseClassGenerator {

    /**
     * Generate a String that contains source code for the Interface
     *
     * @param domainStructure
     * @return
     */
    public abstract String generateTextForInterface(DomainStructure domainStructure);

    /*
     get the Interface filename
     */
    public abstract String getInterfaceFileName(DomainStructure domainStructure);

    /**
     * If basic CRUD operations should be implemented in the class
     */
    boolean isBasicCrudOperations;


    /**
     * String variable that shall hold the basic CRUD implementation text for the class
     */
    String basicCRUDImplementationClass;


    /**
     * String variable that shall hold the basic CRUD text for the Interface declaration
     */
    String basicCRUDInterfaceDeceleration;


    /**
     * Text Constants
     */

    final String IMPL = "Impl";

    final String AUTOWIRED = "@Autowired";

    final String INTERFACE = "interface";

    final String REPOSITORY = "Repository";

    final String SUPER = "super";

    final String IMPORT_LIST = "import java.util.List;";

    final String BYID = "getById(";

    final String LIST = "List<";

    final String GETALL = "getAll()";



    /**
     * Return the attribute that is the primary key
     *
     * @param attributes
     * @return
     */

    Attribute findPrimaryKeyAttribute(List<Attribute> attributes) {

        Attribute primaryKeyAttribute = null;


        for (Attribute attribute : attributes) {


            if (attribute.isPrimaryKey()) {

                primaryKeyAttribute = attribute;

                break;
            }


        }

        return primaryKeyAttribute;
    }

}
