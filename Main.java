import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary lWord = new Dictionary();
        DictionaryCommandline show = new DictionaryCommandline();
        DictionaryManagement in = new DictionaryManagement();
        in.insertFromCommandline(lWord);
        show.showAllWords(lWord);
//        Word a = in.dictionaryLookup(lWord, "Hello");
//        System.out.println(a.getWordExplain());
    }
}
