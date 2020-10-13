package sample;

import javafx.scene.Scene;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.io.IOException;

public class dictionarySearch {
    List<Word> list=new ArrayList<>();
    public void insertFromFile() {
        try(Scanner scanner=new Scanner(new File("src/dictionaries.txt"))){
            while (scanner.hasNext()) {
                while (scanner.hasNextLine()) {
                    Word x = new Word();
                    String a = scanner.next();
                    x.setWordTarget(a);
                    String  b = scanner.nextLine();
                    b = b.trim().replaceAll(" +", " ");
                    x.setWordExplain(b);
                    list.add(x);
                }
            }
        }catch (Exception e){
            System.out.println("got an exception!");
        }
    }

    public Word wordlook(String tar){
        return list.stream().filter(word -> tar.equals(word.getWordTarget())).findFirst().orElse(null);
    }
    public void delete(Word w){
        list.remove(w);
    }
    public void suafile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("src/dictionaries.txt", "UTF-8");
        for (int i = 0; i < list.size(); i++) {
            printWriter.println(list.get(i).getWordTarget() + '\t' + list.get(i).getWordExplain());
        }
        printWriter.close();
    }
}
