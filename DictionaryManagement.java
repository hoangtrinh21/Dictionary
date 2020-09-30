import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    void insertFromCommandline(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i ++){
            Word x = new Word();
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            x.setWordTarget(a);
            x.setWordExplain(b);
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

    public void suaXoaThem(Dictionary list){
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Word word1 = wordlook(list, string);
        if (word1 == null){
            System.out.println("Not found");
            System.out.println("hay nhap y nghia cua tu ban vua chon");
            String yNghia = scanner.nextLine();
            Word tuMoi = new Word(string,yNghia);
        }
        else {
            System.out.println(word1.getWordExplain());
            System.out.println("ban co muon sua ,xoa hoac giu nguyen");
            System.out.println("xoa nhan 1 sua nhan 2 giu nguyen 3");
            int n=scanner.nextInt();
            if (n == 1) {
                String sua = scanner.nextLine();
                list.listWord.stream().filter(word -> string.equals(word.getWordTarget())).findFirst().orElse(null).setWordExplain(sua);
            }
            else {
                //list.listWord.stream().filter(word -> tar.equals(word.getWordTarget())).findFirst().orElse(null)
                if (n == 2){
                    //list.listWord.stream().filter(word -> string.equals(word.getWordTarget())).findFirst().orElse(null).
                }
                else {
                    return ;
                }
            }
        }
    }

}
