import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    void insertFromCommandline(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Số từ bạn muốn thêm là: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập từ bạn muốn thêm ");
            String a = scanner.nextLine().toLowerCase();
            if (dictionaryLookup(list, a)) {
                System.out.println("Trong từ điển đã có từ này.");
                continue;
            }
            String b = scanner.nextLine();
            Word x = new Word(a, b);
            list.listWord.add(x);
            System.out.println("Đã thêm " + x.getWordTarget() + "vào từ điển");
        }
        dictionaryExportToFile(list);
    }

    public void insertFromFile(Dictionary list) throws IOException {
        Scanner scanner = new Scanner(Paths.get("dictionaries.txt"),"UTF-8");
        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String a = scanner.next().toLowerCase();
                x.setWordTarget(a);
                String  b = scanner.nextLine();
                b = b.trim().replaceAll(" +", " ");
                x.setWordExplain(b);
                list.listWord.add(x);
            }
        }
        scanner.close();
    }

    public Word wordlook(Dictionary list, String tar){
        Word a = list.listWord.stream().filter(w -> tar.equals(w.getWordTarget())).findFirst().orElse(null);
        return a;
    }

    public boolean dictionaryLookup(Dictionary list, String tar){
        Word a = list.listWord.stream().filter(w -> tar.equals(w.getWordTarget())).findFirst().orElse(null);
        if (a == null) {
            return false;
        }
        return true;
    }

    public void suaXoaThem(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        String w = new String();
        
        while (true) {
            System.out.println("Bạn muốn làm gì tiếp theo?" + '\n'
                        + "1: Sửa từ" + '\n' + "2: Xóa từ" + '\n' + "3: Thêm từ" + '\n' + "4: Trở về");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n == 1) {
                System.out.println("Từ bạn muốn sửa là?");
                w = scanner.nextLine().toLowerCase();
                if (!dictionaryLookup(list, w)) {
                    System.out.println("Từ này không có trong từ điển.");
                } else {
                    Word look = wordlook(list, w);
                    look.print();
                    System.out.print("Nghĩa mới của " + look.getWordTarget() + " là: ");
                    String ex = scanner.nextLine();
                    look.setWordExplain(ex);
                }
            }
            if (n == 2) {
                System.out.print("Từ bạn muốn xóa là: ");
                w = scanner.next().toLowerCase();
                if (!dictionaryLookup(list, w)) {
                    System.out.println("Từ này không có trong từ điển.");
                } else {
                    Word look = wordlook(list, w);
                    list.listWord.remove(look);
                    System.out.println("Đã xóa.");
                }
            }
            if (n == 3) {
                insertFromCommandline(list);
            }
            if (n == 4) {
                dictionaryExportToFile(list);
                break;
            }
        }
    }

    public void dictionaryExportToFile(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter=new PrintWriter("dictionaries.txt","UTF-8");
        for(int i=0;i<list.listWord.size();i++){
            printWriter.println(list.listWord.get(i).getWordTarget().toLowerCase()+'\t'+list.listWord.get(i).getWordExplain().toLowerCase());
        }
        printWriter.close();
    }

}
