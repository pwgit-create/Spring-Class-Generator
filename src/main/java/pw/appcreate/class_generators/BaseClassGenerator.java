package pw.appcreate.class_generators;


import pw.appcreate.model.DomainStructure;

/**
 * Created by Peter on 2020-05-23.
 */
public abstract class BaseClassGenerator {


    /**
     * String var that holds the source code of the class  (once it has been generated)
     */

    String classText;


    /**
     * Generate a String that contains source code for the class
     *
     * @param domainStructure
     * @return
     */
    public abstract String generateTextForClass(DomainStructure domainStructure);

    /*
     get the class filename
     */
    public abstract String getClassFileName(DomainStructure domainStructure);


    /**
     * Text modification constants
     */


    /**
     * New Line
     */
    final String n = "\n";
    /**
     * Space
     */
    final String s = " ";

    /**
     * Start brace with one space and two new lines
     */

    final String sB = s + "{" + n + n;

    /*
    End brace with two new lines
     */
    final String eB = n + n + "}";


    /**
     * Text Constants
     */

    final String PUBLIC = "public";
    final String PUBLIC_ClASS = PUBLIC + s + "class";

    final String MODEL = "Model";

    final String CONVERTER = "Converter";

    final String IMPLEMENTS = "implements";

    final String OVERRIDE = "@Override";

    final String DOTJAVA = ".java";

    final String EXTENDS = "extends";

    final String IMPORT_OPTIONAL ="import java.util.Optional;";

    final String OPTIONAL = "Optional";

    final String RETURN = "return";

    /**
     * EE = Empty ending
     */
    final String EE = "();";

    /**
     * HE = Empty ending with one right side parentheses;
     */

    final String EER = ");";
}
