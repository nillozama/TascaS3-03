package nivell1;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesManagement {

    public static final String PATH_TO_ACTION = System.getProperty("user.dir");
    public static final String FOLDER_DATA = "resources";
    public static final String FILE_NAME_DATA = "floristeria.txt";
    public static final String FULLPATH = PATH_TO_ACTION + "/" + FOLDER_DATA + "/" + FILE_NAME_DATA;
    public static final File tempFile = new File(FULLPATH);
    public static final File FOLDER = new File(PATH_TO_ACTION);

    public static boolean starts() throws IOException {

        if (tempFile.exists()) {

            System.out.println("Todo correcto, tira millas.");
            return true;

        } else if (!checkIfExistDirectory()) {

            // No existe el directorio. Crearlo junto con el archivo.
            File D = new File(PATH_TO_ACTION + "/" + FOLDER_DATA);
            boolean D1 = D.mkdir();

            if (D1) {
                System.out.println("Carpeta creada correctamente");
                return creatingFile(0);

            } else {
                System.out.println("Error creación de carpeta!");
                return false;
            }

        } else {
            // No existe el archivo. Créalo!");
            return creatingFile(0);
        }
    }


    public static boolean creatingFile(int stateOfFile) {

        String encoding = "UTF-8";
        File file = new File(FULLPATH);
        try {
            // A partir del objeto File creamos el fichero físicamente si es nuevo.
            if (stateOfFile == 0) {
                if (file.createNewFile()) {
                    System.out.println("El fichero se ha creado correctamente");
                    return true;

                } else {
                    // Sobreesctibimos el fichero ???
                    PrintWriter writer = new PrintWriter(file, encoding);
                    writer.println("Gustamente y sus músicos");
                    writer.close();
                    return true;
                }
            } else {
                System.out.println("No ha podido ser creado el fichero");
                return false;
            }

        } catch (IOException ioe) {

            ioe.printStackTrace();
            return false;
        }

    }

    public static boolean checkIfExistDirectory() throws IOException {

        return Files.list(Paths.get(PATH_TO_ACTION)).filter(Files::isDirectory)
                .anyMatch(f -> f.endsWith(FOLDER_DATA));
    }


}