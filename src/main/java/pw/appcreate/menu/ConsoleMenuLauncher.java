package pw.appcreate.menu;


import pw.appcreate.Enum.AccessModifier;
import pw.appcreate.class_generators.*;
import pw.appcreate.menu.model.MenuChoice;
import pw.appcreate.model.Attribute;
import pw.appcreate.model.DomainStructure;
import pw.appcreate.util.FileWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuLauncher {

    public ConsoleMenuLauncher() {
    }

    public void launchConsoleMenu() {


        String userInput;


        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("*****ClassGenerator*****");
            System.out.println("*. Press 1 for generating classes from an domain model");
            System.out.println("*. Press 2 to exit");
            System.out.println("Enter your choice:");


            userInput = input.next();


            switch (userInput) {
                case "1":

                    executeClassGeneration(userInput, input);
                    System.out.println("done with job number 1");
                    break;
                case "2":
                    //exit from the program
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    //inform user in case of invalid choice.
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        }
    }


    private void executeClassGeneration(String userInput, Scanner input) {

        boolean isMoreAttributes = true;

        boolean isPrimaryKey;
        String tableName;
        String javaClassName;

        String tempAttributeNameDB ="";
        String tempAttributeNameJava;

        String tempDatatype;

        boolean implementConverter = false;
        boolean implementBasicCrudOperations = false;


        Attribute attribute;

        List<Attribute> attributes = new LinkedList<>();

        if (userInput.equalsIgnoreCase("1")) {

            isPrimaryKey = true;
        } else {

            isPrimaryKey = false;
        }


        System.out.println("Enter the Table name -> ");
        tableName = input.next();

        System.out.println("Enter the Java class name -> ");
        javaClassName = input.next();


        System.out.println("Declare the attributes for your domain model");

        if (isPrimaryKey) {

            System.out.println("Enter primary key name (DB) -> ");
            tempAttributeNameDB = input.next();

        }

        System.out.println("Enter attribute name (Java)");
        tempAttributeNameJava = input.next();

        System.out.println("Enter data type for attribute (Java)");
        tempDatatype = input.next();



        attribute = new Attribute(AccessModifier.PUBLIC, tempDatatype, tempAttributeNameJava, tempAttributeNameDB, isPrimaryKey);

        attributes.add(attribute);

        System.out.println("Add next attribute..");



        while (isMoreAttributes) {

            System.out.println("Enter attribute name(DB) -> ");
            tempAttributeNameDB = input.next();


            System.out.println("Enter attribute name (Java)");
            tempAttributeNameJava = input.next();

            System.out.println("Enter data type for attribute (Java)");
            tempDatatype = input.next();

            attribute = new Attribute(AccessModifier.PUBLIC, tempDatatype, tempAttributeNameJava, tempAttributeNameDB, false);

            attributes.add(attribute);


            System.out.println("Add more attributes? (y/n)");


            if (input.next().equalsIgnoreCase("n")) {

                System.out.println("Implement converter? (y/n)");

                if(input.next().equalsIgnoreCase("y")){

                    implementConverter = true;

                }

                else{implementConverter = false;}

                System.out.println("Implement basic Crud Operations? (y/n)");

                if(input.next().equalsIgnoreCase("y")){

                    implementBasicCrudOperations = true;

                }

                else{implementBasicCrudOperations = false;}

                DomainStructure domainStructure = new DomainStructure(javaClassName,tableName,attributes);


                MenuChoice menuChoice = new MenuChoice(implementConverter,implementBasicCrudOperations);
                generateClasses(domainStructure,menuChoice);

                System.exit(0);
            }


        }


    }

    private void generateClasses(DomainStructure domainStructure, MenuChoice menuChoice){

        Domain domain = new Domain();
        Model model = new Model();
        Converter converter = new Converter(menuChoice.isConvertionImplementation());
        Repository repository = new Repository(menuChoice.isBasicCrudImplementation());
        Service service = new Service(menuChoice.isBasicCrudImplementation());




        try{
            FileWriter.write(domain.getClassFileName(domainStructure),domain.generateTextForClass(domainStructure));
            FileWriter.write(model.getClassFileName(domainStructure),model.generateTextForClass(domainStructure));
            FileWriter.write(converter.getClassFileName(domainStructure),converter.generateTextForClass(domainStructure));
            FileWriter.write(repository.getClassFileName(domainStructure),repository.generateTextForClass(domainStructure));
            FileWriter.write(repository.getInterfaceFileName(domainStructure),repository.generateTextForInterface(domainStructure));
            FileWriter.write(service.getClassFileName(domainStructure),service.generateTextForClass(domainStructure));
            FileWriter.write(service.getInterfaceFileName(domainStructure),service.generateTextForInterface(domainStructure));

        }
        catch (IOException io){io.printStackTrace();}



    }


}



