package A1;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ingrid Rangel Hernández
 * 
 * The UmlFile class is responsible for reading and writing UML data to a file. 
 * It provides methods to read UML statements from a file, write UML statements to a file, and check if the file exists.
 */
public class UmlFile {
    private File file;

    public UmlFile(String path) {
        this.file = new File(path);
    }

    public List<String> read(){
        List<String> temp = new ArrayList<>();
        List<String> relationships = new ArrayList<>();
        try (Scanner input = new Scanner(file)) 
        {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                temp.add(line);
            }
            System.out.println("Uml data read from \n" + file.getAbsolutePath());
        } catch (Exception e) {
           System.out.println("Error reading file: "+e.getMessage());
        }
        for (int i = 1; i < temp.size()-1; i++) {
            relationships.add(temp.get(i));
            
        }
        return relationships;
    }

    public boolean write(List<String> umlStatementList){
        String umlSentences = "@startuml \n";

        for (String statement : umlStatementList) {
            umlSentences += statement + "\n";
        }
        umlSentences += "@enduml";

        try (FileWriter writer = new FileWriter(file)) 
        {
            writer.write(umlSentences);
            System.out.println("Uml data written to \n" + file.getAbsolutePath());
            return true;
        } catch (Exception e) {
           System.out.println("Error writing to file: "+e.getMessage());
           return false;
        }
    }

    public boolean exists(){
        return file.exists();
    }

}   

        
