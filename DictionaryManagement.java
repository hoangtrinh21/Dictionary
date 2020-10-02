import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    void insertFromCommandline(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String a =scanner.nextLine();
            String b = scanner.nextLine();
            Word x = new Word(a,b);
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
        Word a=wordlook(list,tar);
        System.out.println(a.getWordTarget());
    }
    public void dictionaryAdvanced(Dictionary list) throws IOException {
        insertFromFile(list);
        DictionaryCommandline.showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String tar = sc.next();
        dictionaryLookup(list, tar);
    }
    public void suaXoaThem(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        String string=scanner.nextLine();
        Word word1=wordlook(list,string);
        if(word1==null){
            System.out.println("Not found");
            System.out.println("hay nhap y nghia cua tu ban vua chon");
            String yNghia=scanner.nextLine();
            Word tuMoi=new Word(string,yNghia);
            list.listWord.add(tuMoi);
        }
        else{
            System.out.println(word1.getWordExplain());
            System.out.println("ban co muon sua ,xoa hoac giu nguyen");
            System.out.println("xoa nhan 1 sua nhan 2 giu nguyen 3");
            int n=scanner.nextInt();
            if(n==1) {
                String sua=scanner.nextLine();
                word1.setWordExplain(sua);
            }
            else{
                if(n==2){
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

