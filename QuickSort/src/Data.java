import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Data {

    public static void main(String[] args) {
        Data cd = new Data();

        // Pseudozufallszahlen erstellen
        cd.createRandom(100000);
        cd.createRandom(1000000);
        cd.createRandom(5000000);

        // Sortierte Zahlen erstellen
        cd.createSorted(100000);

    }

    void createRandom(int anzahl) {
        Random r = new Random();
        try {
            // https://www.w3schools.com/java/java_files_create.asp
            FileWriter writer = new FileWriter("QuickSort/res/" + anzahl + ".txt");

            for (int i = 0; i < anzahl; i++) {
                writer.write(r.nextInt(5000000) + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Fehler!");
            e.printStackTrace();
        }

    }

    void createSorted(int anzahl) {
        try {
            // https://www.w3schools.com/java/java_files_create.asp
            FileWriter writer = new FileWriter("QuickSort/res/" + anzahl + "_sortiert.txt");

            for (int i = 0; i < anzahl; i++) {
                writer.write(i + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Fehler!");
            e.printStackTrace();
        }

    }

    int[] readFile(String fileName, int anzahl) {

        int[] all = new int[anzahl];

        try {
            // https://www.w3schools.com/java/java_files_read.asp
            Scanner scanner = new Scanner(new File("QuickSort/res/" + fileName));
            int i = 0;
            while (scanner.hasNextInt()) {
                all[i++] = scanner.nextInt();
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Fehler.");
            e.printStackTrace();
        }

        return all;

    }
}
