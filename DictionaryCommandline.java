public class DictionaryCommandline {
    public void showAllWords(Dictionary a) {
        System.out.println("No | English           | Vietnamese");
        for (int i = 0;i < a.listWord.size(); i++){
            System.out.printf("%-3s| %-20s|%s%n", i + 1, a.listWord.get(i).getWordTarget(), a.listWord.get(i).getWordExplain());
        }
    }
}