package A1;

/**
 * Ingrid Rangel Hernández
 * 
 * The UmlTool class serves as the entry point for the UML tool application. 
 * It initializes the UML manager and starts the menu-driven interface for user interaction.
 */
public class UmlTool {
    public static void main(String[] args) {
        java.util.Scanner scrn = new java.util.Scanner(System.in);
        UmlManager umlManager = new UmlManager();
        System.out.println("Welcome to the UML tool");               
        umlManager.menu(scrn);
        System.out.println("Program written by Ingrid Rangel Hernández");
    }
}
