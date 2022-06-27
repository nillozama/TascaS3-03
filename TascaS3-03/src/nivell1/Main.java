package nivell1;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static nivell1.Menu.*;
import static nivell1.Utils.doubleFormatProperly;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private static final String[][] TIPUS_PRODUCTE =
            new String[][]{{"arbre", "Altura"},
                    {"flor", "Color"},
                    {"decoració", "Material - (Fusta o plàstic)"}};
    protected static String valorTotalStock;
    protected static String valorTotalVendes;
    private static List<Producte> stock = new ArrayList<Producte>();
    private static List<Ticket> compresArray = new ArrayList<Ticket>();


    public static void main(String[] args) throws IOException {

        //Check if everything is going fine loading files
        if(FilesManagement.starts())
            updateDataStockSales();
        else
            System.out.println("Pasan cosas malas...");


        /* +-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+
          |S|w|i|t|c|h| |C|o|n|t|r|o|l| |M|e|t|h|o|d|s|
        +-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+ */
        int optionSelected, tipusProducte;
        String tSortir;
        do {
            Menu.screenMenu();
            optionSelected = Menu.inputDataInt("SELECCIONA OPCIÓ (0 - SORTIR): ");
            scanner.nextLine();
            switch (optionSelected) {

                case 1:
                    tipusProducte = previousMenuAfegirProducte();
                    if (tipusProducte != -1) {
                        entradaDadesObjecte(tipusProducte);
                    }
                    break;
                case 2:
                    int indexProducteBorrar = previousMenuRetirarProducte();
                    if (indexProducteBorrar != 0)
                        retirarProducteDelStock(indexProducteBorrar - 1);
                    break;
                case 3:
                    imprimirTotesProductes();
                    break;
                case 4:
                    inventariProductesPerQuantitat();
                    break;
                case 5:
                    valorTotalVendes(true);
                    break;
                case 6:
                    crearTicket();
                    break;
                case 7:
                    System.out.println("mete datos");
                    Test obj1 = crearNouObject("Arbre Nadal", 0, 23.30, "120 cm.");
                    Test obj2 = crearNouObject("Arbre Bonsai", 0, 1500, "20 cm.");
                    Test obj3 = crearNouObject("Rosa Bonita", 1, 3.90, "Blau");
                    Test obj4 = crearNouObject("Test tiesto", 2, 50.00, "Plàstic");
                    Test obj5 = crearNouObject("Test roure", 2, 230.20, "Fusta");
                    Test obj6 = crearNouObject("Lírio Cirio", 1, 80.99, "Blanc");
                    afegirProducteToArray(obj1);
                    afegirProducteToArray(obj2);
                    afegirProducteToArray(obj3);
                    afegirProducteToArray(obj4);
                    afegirProducteToArray(obj5);
                    afegirProducteToArray(obj6);
                    break;
                case 0:
                    System.out.print(" i  A-D-É-U-");
                    scanner.close();
                    break;

            }
        } while (optionSelected != 0);

    }

    private static void updateDataStockSales() {
        valorActualStock();
        valorTotalVendes(false);
    }

    // Getting kind of product to create
    public static int previousMenuAfegirProducte() {

        int optionSelected;
        do {
            printHeadSection("Selecciona el tipo producte a afegir");
            printClassOfProducts();
            optionSelected = Menu.inputDataInt("SELECCIONA OPCIÓ: ");
            scanner.nextLine();

            if (optionSelected == 1) {
                return 0;
            } else if (optionSelected == 2) {
                return 1;
            } else if (optionSelected == 3) {
                return 2;
            }

        } while (optionSelected != 0);

        return -1;
    }

    public static void entradaDadesObjecte(int tipoObjecte) {

        printHeadSection("Creant un " + TIPUS_PRODUCTE[tipoObjecte][0]);

        String peculiaritat;
        System.out.print("Denominació: ");
        String anomenat = scanner.nextLine();

        if (TIPUS_PRODUCTE[tipoObjecte][0].equalsIgnoreCase(TIPUS_PRODUCTE[2][0])) {
            do {
                System.out.print(TIPUS_PRODUCTE[tipoObjecte][1] + ": ");
                peculiaritat = scanner.nextLine();

            } while   (!peculiaritat.equalsIgnoreCase("fusta")
                    && !peculiaritat.equalsIgnoreCase("plàstic"));

        } else {
            System.out.print(TIPUS_PRODUCTE[tipoObjecte][1] + ": ");
            peculiaritat = scanner.nextLine();
        }

        System.out.print("Preu: ");
        double preu = scanner.nextDouble();

        afegirProducteToArray(crearNouObject(anomenat, tipoObjecte, preu, peculiaritat));

    }

    public static Test crearNouObject(String denominacio, int tipoObjecte, double preu, String peculiaritat) {
        Test obj = new Test(denominacio, TIPUS_PRODUCTE[tipoObjecte][0], preu, peculiaritat);
        return obj;
    }

    public static void afegirProducteToArray(Test obj) {
        stock.add(obj);
        updateDataStockSales();
    }

    public static int previousMenuRetirarProducte() {

        printHeadSection("Retirar un producte".toUpperCase());

        int indexToRemove;
        do {

            indexToRemove = Menu.inputDataInt("INTRODUEIX ID (0 - SORTIR): ");
            scanner.nextLine();

        } while (!valorEnRang(indexToRemove) || indexToRemove == 0);

        return indexToRemove;
    }

    public static void retirarProducteDelStock(int i) {
        stock.remove(i);
        updateDataStockSales();
    }

    public static void imprimirTotesProductes() {

        Menu.imprimirProductesHead();

        int i = stock.size();
        /* Sorting in decreasing order to see the last one added*/
        Collections.reverse(stock);
        // Printing data
        for (Producte producte : stock
        ) {
            System.out.printf("%-3d %-20s %-15s %-15s %10.2f\n",
                    i,
                    producte.getNom(),
                    ((Test<?>) producte).getObject(),
                    producte.getTipus(),
                    producte.getPreu());
            i--;
        }
        // Back to original distribution
        Collections.reverse(stock);
        // Pausing screen scroll
        if (stock.size() > 25)
            Menu.pressEnterToContinue();

    }

    // Returns value of stock in terms of currency
    public static String valorActualStock() {
        return valorTotalStock = doubleFormatProperly(stock.stream()
                .mapToDouble(Producte::getPreu).sum());
    }
    // Returns value of sales in terms of currency
    public static void valorTotalVendes(boolean isPrinting) {

        double totalVendes = 0, totalAcumulat = 0;

        if (isPrinting) {
            System.out.println();
            printHeadSection("Tickets totales emesos: ".toUpperCase() + compresArray.size());
        }

        int i = 1;
        for (Ticket ticket : compresArray) {

            List<Producte> comprat = (ticket.getCompres());
            totalVendes   = Ticket.totalPerCompre(comprat);
            totalAcumulat+= totalVendes;

            if(isPrinting){
                System.out.printf("%3d: %19s\n", i, doubleFormatProperly(totalVendes));
                printLines(52, '-');
            }
            i++;
        }

        valorTotalVendes = doubleFormatProperly(totalAcumulat);
    }

    public static void inventariProductesPerQuantitat() {
        Map<String, Map<String, Long>> quantitatPerProducte =
                stock.stream()
                        .collect(Collectors.groupingBy(Producte::getTipus, Collectors.mapping(p -> p, Collectors.toList())))
                        .entrySet()
                        .stream()
                        .map(entry -> new KeyValuePair(entry.getKey(), entry.getValue().stream().collect(Collectors.groupingBy(Producte::getNom, Collectors.counting()))))
                        .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));

        Utils.imprimirInventari(quantitatPerProducte);
    }

    public static void crearTicket() {

        if (stock.size() > 0) {

            Ticket ticket = new Ticket(compresArray.size() + 1);
            int valueSelected;
            String producteSeleccionat;

            do {
                imprimirTotesProductes();
                printHeadSection("Introdueix ID producte per afegir al carro");
                valueSelected = Menu.inputDataInt("Escriu ID [ 0 - SORTIR]: ");

                if (valueSelected == 0) {

                    if (ticket.getCompres().size() > 0) {
                        System.out.println("Acabant la compra...Registrant el ticket...");
                        compresArray.add(ticket);
                    } else {
                        System.out.println("Sortint. No se ha fet cap compra...");
                    }

                } else if (valorEnRang(valueSelected)) {

                    producteSeleccionat = stock.get(valueSelected - 1).getNom();
                    ticket.afegirProducte(stock.get(valueSelected - 1));
                    retirarProducteDelStock(valueSelected - 1);
                    System.out.println("\nProducte: ".toUpperCase()
                            + ANSI_BLUE + producteSeleccionat
                            + ANSI_RESET +", afegit correctament.");
                } else {

                    System.out.println("S'ha introduït un ID incorrecte");
                }
            } while (valueSelected != 0 && stock.size() > 0);

            //Print the ticket and updating values
            ticket.mostrarProductes();
            updateDataStockSales();

        } else
            System.out.println(ANSI_RED + "\nNo n'hi ha productes per vendre." +
                    "Botiga buida!! \n\n".toUpperCase()
                    + ANSI_RESET);
    }


    // To select the right index to remove product from array
    public static boolean valorEnRang(int number) {
        return (number >= 1 && number <= stock.size());
    }

}



