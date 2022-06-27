package nivell1;

import java.util.InputMismatchException;
import static nivell1.Main.valorTotalStock;
import static nivell1.Main.valorTotalVendes;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m", ANSI_YELLOW = "\u001B[33m", ANSI_BLUE = "\u001B[32m", ANSI_RED = "\u001B[31m";

    public static void screenMenu() {

        System.out.println();

        System.out.println("               __/)  \n" +
                "            .-(__(=:       " + "\u001B[32m" + "FLORISTERIA PEPA \n" + "\u001B[0m" +
                "            |    \\)\n" +
                "     (\\__  |               1 - AFEGIR PRODUCTE" +
                "\n" +
                "     :=)__)-|  __/)        2 - RETIRAR PRODUCTE\n" +
                "      (/    |-(__(=:       3 - IMPRIMIR STOCK TOTES PRODUCTES\n" +
                "    ______  |  _ \\)        4 - IMPRIMIR STOCK AMB QUANTITATS\n" +
                "   /      \\ | / \\          5 - MOSTRAR COMPRES ANTIGUES\n" +
                "        ___\\|/___\\\n" +
                "       [         ]\\        6 - CREAR TIQUET DE VENDA\n" +
                "        \\       /  \\\n" +
                "         \\     /        " + String.format("%s: %9s ",  "   TOTAL VENUT",  valorTotalVendes)  +
                "\n          \\___/            " + String.format("%s %9s","VALOR STOCK:" + ANSI_BLUE,  valorTotalStock) + ANSI_RESET
                + (valorTotalStock.equalsIgnoreCase("0,00") ? (ANSI_RED + " (Botiga buida)" + ANSI_RESET) : "") + "\n" +
                "--------------------------------------------------------------");

    }

    public static void printClassOfProducts() {
        System.out.println(tabulate() + " ARBRE  -  FLOR  -  DECORACIÓ  - " + ANSI_BLUE + " SORTIR" + ANSI_RESET);
        System.out.println(tabulate() + "  [1]       [2]        [3]     "   + ANSI_BLUE + "    [ 0 ]" );
        System.out.println(tabulate() + printSymbol('-', 52)   + ANSI_RESET);
    }

    public static int inputDataInt(String missatge) {
        // Collect data of Integer type
        int dataInput = -1;
        boolean correct = false;

        while (!correct) {
            try {
                System.out.print(tabulate() + missatge);
                dataInput = Main.scanner.nextInt();
                if (dataInput < 1 && dataInput != 0) {
                    System.out.println(tabulate() + "\u001B[31m" + "¡Número mes grand de 0!" + "\u001B[0m");
                    correct = true;

                } else {

                    return dataInput;
                }

            } catch (InputMismatchException e) {
                Main.scanner.nextLine();
                System.out.println(tabulate() + "\u001B[31m" + "¡Error de formato de entrada!" + "\u001B[0m");

            }
        }
        return dataInput;
    }

    public static String tabulate() {
        return "\t\t";
    }

    public static void printHeadSection(String s) {
        printLines(52,'─');
        System.out.println( s + " *** " + ANSI_RESET + "");
        printLines(52,'─');
    }
    public static String printSymbol(char e, int nTimes) {
        return String.valueOf(e).repeat(nTimes);
    }

    public static void printLines(int nTimes, char sSymbol){
        System.out.println( ANSI_YELLOW + printSymbol(sSymbol, nTimes) + ANSI_RESET);
    }

    public static void imprimirProductesHead() {

        System.out.println(ANSI_YELLOW + "\n\n\n\t\tLLISTAT DE PRODUCTES ");
        printLines( 75,'-');

            System.out.printf("%-3s %-20s %-15s %s %19s\n", "ID", " NOM"
                    , "PECULIARITAT" , " TIPUS", "PREU");
        printLines( 75,'-');
    }

    public static void imprimirTiquetHead() {

        System.out.println(ANSI_YELLOW + "\n\n\n\t\tTIQUET DE COMPRE ");
        System.out.println( printSymbol('-', 75));

        System.out.printf("%-6s %-20s %-15s %10s\n", "LÍNEA", "NOM"
                , "PECULIARITAT" , "PREU");

        System.out.println(ANSI_YELLOW + printSymbol('-', 75) + ANSI_RESET);
    }

    public static void pressEnterToContinue() {
        System.out.print("\n\tPrem Enter per continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
