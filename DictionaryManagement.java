import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {

    void insertFromCommandline(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        Word x = new Word();
        for(int i=0;i<n;i++){
            String a =scanner.nextLine();
            scanner.nextLine();
            String b = scanner.nextLine();

            x.setWordTarget(a);
            x.setWordExplain(b);
            list.listWord.add(x);
        }
    }
//    public void addFile() throws IOException {
//        Scanner scanner=new Scanner(Paths.get("dictionaries.txt"),"UTF-8");
//        while(scanner.hasNext()){
//            String a=scanner.nextLine();
//            Word x=new Word();
//            x.setWordTarget();
//        }
//        scanner.close();
//    }
}
