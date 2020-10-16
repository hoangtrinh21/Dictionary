package sample;

import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement in = new DictionaryManagement();
    public void showAllWords(Dictionary a) {
        System.out.println("No | English           | Vietnamese");
        for (int i = 0;i < a.listWord.size(); i++){
            System.out.printf("%-3s| %-20s|%s%n", i + 1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());
        }
    }
    public void dictionaryAdvanced(Dictionary list) throws IOException {
        DictionaryManagement a=new DictionaryManagement();
        a.insertFromFile(list);
        showAllWords(list);
        Scanner sc = new Scanner(System.in);
        String tar = sc.next();
        a.dictionaryLookup(list, tar);
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

//    public List<String> TutiengAnh(Dictionary lWord){
//        List<String> a=new ArrayList<>();
//        for(Word b:lWord.listWord){
//            a.add(b.getWordTarget());
//        }
//        return a;
//    }
}