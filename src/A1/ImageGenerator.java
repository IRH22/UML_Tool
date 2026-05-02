package A1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Ingrid Rangel Hernández
 * 
 * The ImageGenerator class is responsible for generating UML images based on the provided UML statements. 
 * It allows users to set the output format (PNG or SVG) and create an image file with the specified name and format.
 */

public class ImageGenerator {
    private String name;
    private FileFormatOption formatOption;
    private String extension;
    private static final FileFormatOption PNG_FORMAT = new FileFormatOption(FileFormat.PNG);
    private static final FileFormatOption SVG_FORMAT = new FileFormatOption(FileFormat.SVG);

    public ImageGenerator() {
        formatOption = PNG_FORMAT;
        extension = "png";
    }

    public boolean setOutputFormat(String format) {
        switch (format) {
            case "png":
                formatOption = PNG_FORMAT;
                extension = "png";
                return true;
            case "svg":
                formatOption = SVG_FORMAT;
                extension = "svg";
                return true;
            default:
                System.out.println("Unknown format");
                return false;
        }
    }

    public void createImage(String name, List<String> umlStatementList) {
        this.name = name;
        String uml = "@startuml\n";
        for (String statement : umlStatementList) {
            uml += statement + "\n";
        }
        uml += "@enduml";
        SourceStringReader reader = new SourceStringReader(uml);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();     

        String file = "uml_files\\" + this.name + "." + extension;
        Path path = Paths.get(file);
        try
        {
        reader.generateImage(os, formatOption);
        os.close();
        Files.write(path, os.toByteArray());
        System.out.println(extension.toUpperCase() + " file generated successfully:\n" + path.toAbsolutePath());
        } catch (IOException e) {
        System.out.println("Write failed - "+e.getMessage());
        }
    }
}
