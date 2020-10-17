package sample;

public class Word {
    private String word_target;
    private String word_explain;

    public String getWordTarget() {
        return this.word_target;
    }

    public String getWordExplain() {
        return this.word_explain;
    }

    public void setWordTarget(String x) {
        this.word_target = x;
    }

    public void setWordExplain(String x) {
        this.word_explain = x;
    }

    public Word() {
        this.word_target = " ";
        this.word_explain = " ";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public void print(){
        System.out.println(this.word_target + "   " + this.word_explain);
    }
}
