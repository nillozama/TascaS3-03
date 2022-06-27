package nivell1;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import static nivell1.Menu.*;

public class Utils {


    // Format a double value for european currency
    protected static String doubleFormatProperly(double totalStock) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setMaximumFractionDigits(2);
        return df.format(totalStock);
    }

    //ACTION TO PRINT INVENTORY
    protected static void imprimirInventari(Map<String, Map<String, Long>> quantitatPerProducte) {
        System.out.println("\n" +
                "\n" + ANSI_BLUE +
                "╦┌┐┌┬  ┬┌─┐┌┐┌┌┬┐┌─┐┬─┐┬\n" +
                "║│││└┐┌┘├┤ │││ │ ├─┤├┬┘│\n" +
                "╩┘└┘ └┘ └─┘┘└┘ ┴ ┴ ┴┴└─┴" +
                ANSI_RESET);

        for (Map.Entry<String, Map<String, Long>> key : quantitatPerProducte.entrySet()) {
            printHeadSection(key.getKey().toUpperCase());

            Set<Map.Entry<String, Long>> newKey = key.getValue().entrySet();
            for (Map.Entry<String, Long> ping : newKey
            ) {
                System.out.printf("%-15s : %s\n", ping.getKey(), ping.getValue());
            }
        }
        Menu.pressEnterToContinue();
    }

}
