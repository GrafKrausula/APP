//package app.exercise.testing;
/**
 * Die Java-Klasse BSTree.
 *
 * @author Martin Krause
 */

import app.exercise.algebra.*;
import app.exercise.adt.*;
import app.exercise.visualtree.*;
import java.util.*;


 /**
  * Diese Klasse realisiert einen binären Suchbaum.
  */
public class BSTreeTester{
        public static void main( String[] args ) {

// == 1) Manuelle Eingabe der Zahlen ===========================================

                System.out.println("Testklasse lauft. Bitte Zahlen eingeben:");

                // Grafische Darstellung mit RedBlackTreeDrawer
                RedBlackTreeDrawer<CompRational> visual = new RedBlackTreeDrawer<CompRational>();
                CompRational cr = new CompRational();

                // Lese Eingabe ein
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                // Teile Eingabe an Leerzeichen
                String[] snumbers = input.split(" ");

                // Wandele Eingabe in Doubles um
                List<Long> numbers = new ArrayList<Long>();
                List<CompRational> complist = new ArrayList<CompRational>();

                // Wandel Stringliste in Longliste um.
                for(String i : snumbers) {
                        numbers.add(Long.parseLong(i));
                }

                // Generate Random Numbers
                Random r = new Random();

                // Pruefe auf gerade Anzahl der Elemente
                if (numbers.size() % 2 != 0) {
                        throw new UnsupportedOperationException("Uneven number in input stream!");
                } else {
                        // Füge EingabeStream in Liste
                        int i = 0;
                        while (i < numbers.size() - 1) {
                                cr = new CompRational(numbers.get(i), numbers.get(i+1));
                                i += 2;
                                complist.add(cr);
                        }

                        // Ausgabe der Liste der CompRationals
                        System.out.println(complist);

// == 2) Erstellen des Primärbaums und der Sekundärbäume =======================

                        // Erstellen der Bäume
                        BSTree<CompRational> bst = new BSTree<CompRational>();  // Primärbaum
                        BSTree<CompRational> bst1 = new BSTree<CompRational>(); // Sekundärbaum 1
                        BSTree<CompRational> bst2 = new BSTree<CompRational>(); // Sekundärbaum 2

                        // Hilfsvariablen
                        CompRational first_cr = new CompRational(complist.get(0));
                        CompRational last_cr;

                        CompRational max_cr = new CompRational();
                        CompRational min_cr = new CompRational();

                        // Hilfsvariable zum Speichern der nächsten CompRational
                        CompRational nex = new CompRational();

                        int sgn = 1; // Hilfsvariable zum wechseln der Sekundärbäume.
                        boolean minfound = false;

                        // Hinzufügen der Comprationals zum Baum
                        for (Iterator<CompRational> j = complist.iterator(); j.hasNext();) {

                                // Hole nächste Zahl aus EingabeStream
                                nex = j.next();

                                // Füge sie dem Primären Baum hinzu
                                bst.add(nex);

                                // Füge sie abwechselnd den sekundären Bäumen hinzu
                                if (sgn == 1) {
                                        bst1.add(nex);
                                } else {
                                        bst2.add(nex);
                                }
                                sgn *= -1;

                                // Aktualisieren der Anzeige
                                visual.draw(bst.current);

                                scanner.nextLine(); // Warten
                        }

                        // Speichere letzte Zahl des Eingabestreams
                        last_cr = new CompRational(nex);

// == 3) Ausgabe des Primärbaums und der Sekundärbäume auf der Konsole =========

                        // Test Iterator
                        System.out.println("Ausgabe bst (Primärer Testbaum):");

                        // Erste Zahl des Eingabestreams holen
                        Iterator<Knoten> kk = bst.iterator();
                        if (kk.hasNext()) {
                                min_cr = kk.next().getValue(); // kleinste Zahl ermitteln
                                System.out.println("Knoten: " + min_cr);
                        }

                        // Rest Primärbaum
                        for (; kk.hasNext();) {
                                max_cr = kk.next().getValue(); // groesste Zahl ermitteln
                                System.out.println("Knoten: " + max_cr);
                        }

                        // Sekundärbaum 1
                        System.out.println("Ausgabe bst1 Sekundärer Testbaum:");
                        for (kk = bst1.iterator(); kk.hasNext();) {
                                System.out.println("Knoten: " + kk.next().getValue().toString() + " ");
                        }

                        // Sekundärbaum 2
                        System.out.println("Ausgabe bst2 Sekundärer Testbaum:");
                        for (kk = bst2.iterator(); kk.hasNext();) {
                                System.out.println("Knoten: " + kk.next().getValue().toString() + " ");
                        }

// == 4) Test contains und containsAll =============================== =========

                        // Test Size
                        System.out.println("\nSize: Die Größe des Primärbaums ist: " + bst.size());

                        // Test toArray
                        for(Object o : bst.toArray()) {
                                System.out.println("toArray: " + ((Knoten) o).getValue());
                        }


                        // Test contains
                        System.out.println("\nfirst: Ist " + first_cr +
                                                           " enthalten? " + bst.contains(first_cr));
                        System.out.println("last: Ist " + last_cr +
                                                           " enthalten? " + bst.contains(last_cr));

                        // Test containsAll
                        System.out.println("\nbst1 in bst? " + bst.containsAll(bst1));
                        System.out.println("\nbst2 in bst? " + bst.containsAll(bst2));

// == 6) Test first.remove() und last.remove() =================================

                        System.out.println("Entferne nun first und last. Press Enter!");

                        scanner.nextLine();

                        bst.remove(first_cr);
                        visual.draw(bst.current);
                        System.out.println(first_cr + " entfernt. Press Enter!");

                        scanner.nextLine();
                        bst.remove(last_cr);
                        visual.draw(bst.current);
                        System.out.println(last_cr + " entfernt.");

// == 7) 100 zufällige CompRationals ===========================================

                        System.out.println("Erzeuge 100 zufällige Zahlen zwischen " + min_cr
                                                                + " und " + max_cr + ":");

                        for ( int z = 1; z <= 100; z ++ ) {
                        cr.random(min_cr, max_cr);
                                System.out.println (z + ") " + cr);
                                if (bst.contains(cr)) {
                                        System.out.println(cr + " ist in BSTree. Press Enter to delete!");
                                        scanner.nextLine();
                                        bst.remove(cr);
                                        visual.draw(bst.current);
                                }
                        }
                        System.out.println("Program finish");
                }
        }
}
