import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    void insertFromCommandline(Dictionary list){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            Word x = new Word();
            String a =scanner.nextLine();
            String b = scanner.nextLine();
            x.setWordTarget(a);
            x.setWordExplain(b);
            list.listWord.add(x);
        }
    }
    public void addFile(Dictionary list) throws IOException {
        Scanner scanner=new Scanner(Paths.get("C:\\Users\\leman\\IdeaProjects\\lemanhlinh\\src\\dictionaries.txt"),"UTF-8");while(scanner.hasNext()){
            while(scanner.hasNextLine()){
                Word x=new Word();
                String a=scanner.next();
                x.setWordTarget(a);
                String b=scanner.nextLine();
                for(int i=0;i<10;i++){
                    if(!Character.toString(b.charAt(i)).equals(" ")){
                        String c=b.substring(i);
                        x.setWordExplain(c);
                        break;
                    }
                }
                list.listWord.add(x);
            }
        }
        scanner.close();
    }
}
// hel lo    xin chao