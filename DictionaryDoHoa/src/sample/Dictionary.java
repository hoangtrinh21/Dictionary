package sample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Dictionary {
    public static List<Word> listWord = new ArrayList<Word>();

    public static void loadFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/sample/dictionaries.txt"));
        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String a = scanner.next();
                x.setWordTarget(a);
                String  b = scanner.nextLine();
                b = b.trim().replaceAll(" +", " ");
                x.setWordExplain(b);
                listWord.add(x);
            }
        }
        scanner.close();
    }
}