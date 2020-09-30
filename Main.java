import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary lWord = new Dictionary();
        DictionaryCommandline show = new DictionaryCommandline();
        DictionaryManagement in = new DictionaryManagement();
        in.insertFromCommandline(lWord);
        show.showAllWords(lWord);
        Scanner sc = new Scanner(System.in);
        String tar = sc.nextLine();
        in.dictionaryLookup(lWord, tar);
    }
}
