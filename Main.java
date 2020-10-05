import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(System.in);
        Dictionary lWord = new Dictionary();
        DictionaryCommandline show = new DictionaryCommandline();
        DictionaryManagement in = new DictionaryManagement();
        in.insertFromFile(lWord);
        //show.showAllWords(lWord);
        while (true) {
            System.out.println("Bạn muốn làm gì?" + '\n' +
                                "1: Tra từ" + '\n' + 
                                "2: Sửa, thêm , xóa" + '\n' +
                                "3: Thoát");
            int x = scan.nextInt();
            scan.nextLine();
            if (x == 1) {
                System.out.println("Từ bạn muốn tìm là?");
                String search = scan.nextLine().toLowerCase();
                
                show.dictionarySearcher(lWord, search);
            }
            if (x == 2) {
                in.suaXoaThem(lWord);
            }
            if (x == 3) {
                return;
            }
        }
    }
}
