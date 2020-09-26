import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Dictionary a=new Dictionary();
        DictionaryCommandline b=new DictionaryCommandline();
        DictionaryManagement c=new DictionaryManagement();
        c.insertFromCommandline(a);
        b.showAllWords(a);
    }
}
