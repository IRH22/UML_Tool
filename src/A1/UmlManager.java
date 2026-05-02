package A1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ingrid Rangel Hernández
 * 
 * The UmlManager class manages the UML statements and provides a menu-driven interface for the user to interact with. 
 * It allows users to add relationships, delete UML statements, display UML statements, change image format, read from a UML file, write to a UML file, and save UML images.
 */

public class UmlManager {
    private List<String> umlStatementList = new ArrayList<>();
    private ImageGenerator imageGenerator = new ImageGenerator();

    public void menu(Scanner scrn){
        int opcion;
        do{
            System.out.println("\nPlease select one of the following: \n"+
                            "1: Add relationships \n"+
                            "2: Delete UML statement \n"+
                            "3: Display UML statements \n"+
                            "4: Change image format \n"+
                            "5: Read from UML file \n"+
                            "6: Write to UML file \n"+
                            "7: Save UML image \n"+
                            "0: Quit");
            System.out.print("Enter selection: ");
            if (scrn.hasNextInt()) {
                opcion = scrn.nextInt();
            }else{
                opcion = 1898758;
            }
            scrn.nextLine(); 
            
            switch (opcion){
                case 1:
                    addRelationships(scrn);
                    break;
                case 2:
                    deleteUml(scrn);
                    break;
                case 3:
                    displayUml();
                    break;
                case 4:
                    changeOutputFormat(scrn);
                    break;
                case 5:
                    readFile(scrn);
                    break;
                case 6:
                    writeFile(scrn);
                    break;
                case 7:
                    saveImage(scrn);
                    break;
                case 0:
                    System.out.println("Exiting the UML tool.");
                    break;
                case 1898758:
                    System.out.println("Please enter an integer");
                    break;
                default:
                    System.out.println("Please select a value from 0 to 7");
            }
        }while(opcion!=0);
    }

    private void addRelationships(Scanner scrn){
        int i=umlStatementList.size()+1;
        String statement = "holo";
        while (!statement.equals("")) {
            System.out.print("Enter relationship #"+i+": ");
            statement = scrn.nextLine();
            
            String verbs[] = {" is a "," has a ", " composed of ", " uses ", " depends on "};
            boolean esta = false;
            for (String verb : verbs) {
                if (statement.contains(verb)) {
                    String[] parts = create(statement, verb);
                    esta = true;
                    switch (verb) {
                        case " is a ":
                            umlStatementList.add(parts[1] + " <|-- " + parts[0]);
                            break;
                        case " has a ":
                            umlStatementList.add(parts[0] + " o-- " + parts[1]);
                            break;
                        case " composed of ":
                            umlStatementList.add(parts[0] + " *-- " + parts[1]);
                            break;
                        case " uses ":
                            umlStatementList.add(parts[0] + " ..> " + parts[1]);
                            break;
                        case " depends on ":
                            umlStatementList.add(parts[0] + " <.. " + parts[1]);
                            break;
                        default:
                            break;
                    }
                    i++;
                    break;
                }
            }
            if (!esta && !statement.equals("")) {
                System.err.println("Unknown relationship");
            }
            
        }
    }

    private String[] create(String statement,String verb){
        String partes[] = statement.split(verb);
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].contains(" ")) {
                partes[i] = partes[i].replaceAll(" ", "_");
            }
        }
        return partes;
    }

    private void deleteUml(Scanner scrn){
        if (umlStatementList.size()>0) {
            displayUml();
            while (true) {
                System.out.print("Which UML statement do you want to remove? ");
                int OpDel = scrn.nextInt();
                if (OpDel>0 && OpDel<=umlStatementList.size()) {
                    umlStatementList.remove(OpDel-1);
                    break;
                }else{
                    System.err.println("Invalid integer, please enter a value between 1 and "+umlStatementList.size());
                }
            }
        }else{
            System.err.println("No UML statements to delete");
        }


    }

    private void displayUml(){
        if (umlStatementList.size()>0) {
            System.out.println(" \n UML Statements \n------------------");
            System.out.println("@startuml");
            int i = 1;
            for (String statement : umlStatementList) {
                System.out.println(i + ") " + statement);
                i++;
            }
            System.out.println("@enduml");
        }else{
            System.err.println("No UML statements to display");
        }
    }

    private void changeOutputFormat(Scanner scrn){
        System.out.print("Which image file format do you want (png or svg)? ");
        String formTem = scrn.nextLine();
        if (formTem.equals("png")) {
            imageGenerator.setOutputFormat("png");
        }else if (formTem.equals("svg")) {
            imageGenerator.setOutputFormat("svg");
        }else{
            System.err.println("Keeping previous format ");
        }
    }

    private void readFile(Scanner scrn){
        System.out.print("What is name of the UML file(no extension)? ");
        String name = scrn.nextLine();
        String path = "uml_files\\" + name + ".pu";
        UmlFile umlFile = new UmlFile(path);
        if (umlFile.exists()) {
            umlStatementList.clear();
            umlStatementList.addAll(umlFile.read());
        }else{
            System.err.println("UML file does not exist");
        }
    }

    private void writeFile(Scanner scrn){
        Boolean write = false;
        while (!write) {
            System.out.print("What is name of the UML file(no extension)? ");
            String name = scrn.nextLine();
            String path = "uml_files\\" + name + ".pu";
            UmlFile umlFile = new UmlFile(path);
            if (!umlFile.exists()){
                write = umlFile.write(umlStatementList);
            }else{
                System.err.println("UML file already exists, please choose another name");
            }
        }
    }

    private void saveImage(Scanner scrn){
        if (umlStatementList.size()>0) {
            System.out.print("What is name of the UML image file(no extension)? ");
            String name = scrn.nextLine();
            imageGenerator.createImage(name, umlStatementList);
        }else{
            System.err.println("No UML to create image");
        }
        
    }
}
