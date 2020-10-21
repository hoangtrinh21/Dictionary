package sample;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryCommandline {
    DictionaryManagement in = new DictionaryManagement();
    public void showAllWords(Dictionary a) {
        System.out.println("------------------------------------------------");
        System.out.println("No | English           | Vietnamese");
        for (int i = 0;i < a.listWord.size(); i++){
            System.out.printf("%-3s| %-20s| %s%n", i + 1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());
        }
    }

    public void dictionaryAdvanced(Dictionary list) throws IOException {
        in.insertFromFile(list);
        showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String tar = sc.next().toLowerCase();
        in.dictionaryLookup(list, tar);
    }

    public void dictionaryBasic(Dictionary list) throws FileNotFoundException, UnsupportedEncodingException {
        in.insertFromCommandline(list);
        showAllWords(list);
    }
    public List<String> wordSearcher(Dictionary list,String s){
        List<String> st =new ArrayList<>();
        int n=s.length();
        for(Word i: list.listWord){
            if(i.getWordTarget().length()>=n){
                String string=i.getWordTarget().substring(0,n);
                if(string.equals(s)){
                    Word word=new Word(i.getWordTarget(),i.getWordExplain());
                    st.add(word.getWordTarget());
                }
            }
        }
        return st;
    }

    public List<Word> dictionarySearcher(Dictionary a, String search) {
        List<Word> wordList = new ArrayList<>();
        for (Word i : a.listWord) {
            if (i.getWordTarget().startsWith(search)) {
                wordList.add(i);
            }
        }
        return wordList;
    }

    public String goiYTuSai(Dictionary lword,String s){
        String goiytusai = "Không có từ nào cả ! gợi ý từ bị sai"+'\n';
        int n = s.length();
        List<Word> listGoiY=dictionarySearcher(lword,"");
        for(int i = 0; i < n - 2; i++){
            for(int j = 0; j < i + 1; j++){
                String a = s.substring(j, j + n - 1 -i);
                for(int k = 0; k < listGoiY.size(); k++){
                    int posof = -1;
                    posof = listGoiY.get(k).getWordTarget().indexOf(a);
                    if(posof != -1){
                        goiytusai += listGoiY.get(k).getWordTarget() + '\n';
                        listGoiY.remove(listGoiY.get(k));
                        if(listGoiY.isEmpty()) return  goiytusai;
                    }

                }
            }
        }
        return  goiytusai;
    }
}