import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    void insertFromCommandline(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i ++) {
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            Word x = new Word(a, b);
            list.listWord.add(x);
        }
    }

    public void insertFromFile(Dictionary list) throws IOException {
        Scanner scanner = new Scanner(Paths.get("dictionaries.txt"),"UTF-8");
        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String a = scanner.next();
                x.setWordTarget(a);
                String  b = scanner.nextLine();
                // for (int i = 0; i < b.length(); i++) { // xoa khoang trang thua
                //     if (!Character.toString(b.charAt(i)).equals(" ")) {
                //         String c = b.substring(i);
                //         x.setWordExplain(c);
                //         break;
                //     }
                // }
                b = b.trim().replaceAll(" +", " ");
                x.setWordExplain(b);
                list.listWord.add(x);
            }
        }
        scanner.close();
    }

    public Word wordlook(Dictionary list, String tar){
        return list.listWord.stream().filter(word -> tar.equals(word.getWordTarget())).findFirst().orElse(null);
    }

    public void dictionaryLookup(Dictionary list, String tar){
        Word a = wordlook(list, tar);
        a.print();
    }

    public void dictionaryAdvanced(Dictionary list) throws IOException {
        insertFromFile(list);
        DictionaryCommandline.showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String tar = sc.next();
        dictionaryLookup(list, tar);
    }

    public void suaXoaThem(Dictionary list){
        Scanner scanner = new Scanner(System.in);
        String tudexoa = scanner.nextLine();
        Word word1 = wordlook(list, tudexoa);
        if (word1 == null){
            System.out.println("Not found");
            System.out.println("Hãy nhập ý nghĩa của từ bạn vừa tìm");
            String yNghia = scanner.nextLine();
            Word tuMoi = new Word(tudexoa, yNghia);
            list.listWord.add(tuMoi);
        }
        else {
            System.out.println(word1.getWordExplain());
            System.out.println("Bạn có muốn sửa, xóa hoặc giữ nguyên?");
            System.out.println("Sửa nhấn 1 /n Xóa nhấn 2 /n Giữ nguyên nhấn 3");
            int n = scanner.nextInt();
            if (n == 1) {
                String sua = scanner.nextLine();
                word1.setWordExplain(sua);
            }
            else {
                //list.listWord.stream().filter(word -> tar.equals(word.getWordTarget())).findFirst().orElse(null)
                if (n == 2) {
                    //list.listWord.stream().filter(word -> string.equals(word.getWordTarget())).findFirst().orElse(null).
                    list.listWord.remove(word1);
                }
            }
        }
    }

    public void suafile(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter=new PrintWriter("dictionaries.txt","UTF-8");
        for(int i=0;i<list.listWord.size();i++){
            printWriter.println(list.listWord.get(i).getWordTarget()+'\t'+list.listWord.get(i).getWordExplain());
        }
        printWriter.close();
    }

}
