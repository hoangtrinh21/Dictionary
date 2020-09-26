public class DictionaryCommandline {
    public void showAllWords(Dictionary a) {
        int i=0;
        System.out.println("No     | English           | Vietnamese");
        while(a.listWord.get(i)!=null){
            //System.out.print(i+1 + "\t|");
            //System.out.printf("%20s",a.listWord.get(i).getWordTarget());
            System.out.printf("%-3s| %-20s|%s%n", i+1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());

            i++;
        }
    }
}