import java.io.File;
import java.util.Scanner;

public class AccesFichier {

    public static void main(String[] args) {
        try {
            File fichier = new File("message.txt");
            Scanner readFile = new Scanner(fichier);

            while (readFile.hasNextLine()) {
                String data = readFile;

                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}