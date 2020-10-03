import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class DictionaryCommandline {
    DictionaryManagement in = new DictionaryManagement();
    public static void showAllWords(Dictionary a) {
        System.out.println("No | English           | Vietnamese");
        for (int i = 0;i < a.listWord.size(); i++){
            System.out.printf("%-3s| %-20s|%s%n", i + 1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());
        }
    }
    
    public void dictionaryAdvanced(Dictionary list) throws IOException {
        in.insertFromFile(list);
        showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String tar = sc.next();
        in.dictionaryLookup(list, tar);
    }

    public void dictionaryBasic(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        in.insertFromCommandline(list);
        showAllWords(list);
    }
}