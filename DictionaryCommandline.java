public class DictionaryCommandline {
    public void showAllWords(Dictionary a) {
        int i=0;
        System.out.println("No | English           | Vietnamese");
        while(a.listWord.get(i)!=null){
            System.out.printf("%-3s| %-20s|%s%n", i+1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());
            i++;
        }
    }
}