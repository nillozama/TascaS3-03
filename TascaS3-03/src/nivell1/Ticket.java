package nivell1;


import java.util.ArrayList;
import java.util.List;

import static nivell1.Utils.doubleFormatProperly;

public class Ticket {


    private ArrayList<Producte> compres;
    private int id;

    public Ticket(int id) {

        this.compres = new ArrayList<Producte>();
        this.id = id;
    }


    public void afegirProducte(Producte producte) {
        compres.add(producte);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Producte> getCompres() {
        return compres;
    }

    public void mostrarProductes() {

        Menu.imprimirTiquetHead();
        int i = 1;
        for (Producte producte : compres
        ) {
            System.out.printf("%-6d %-20s %-15s %10.2f\n",
                    i,
                    producte.getNom(),
                    ((Test<?>) producte).getObject(),
                    producte.getPreu());
            i++;
        }
        System.out.printf("\n%-44s %9s\n", "TOTAL TIQUET:",
                doubleFormatProperly(totalPerCompre(compres)));
        Menu.printLines(55,'-');

        //compres.forEach(System.out::println); imprimir para archivo
    }

    public static double totalPerCompre(List<Producte> llista){

        return llista.stream().mapToDouble(Producte::getPreu).sum();
    }

    public String toString() {
        return "id=" + id;
    }

}
