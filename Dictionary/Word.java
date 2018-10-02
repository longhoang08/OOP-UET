import java.util.*;

public class Word {
    private String word_target;
    private ArrayList<String> word_explain;

    public Word(String wt, String we) {
        word_explain = new ArrayList<String>();
        word_target = wt;
        word_explain.add(we);
    }

    // setters
    public void setWordTarget(String wt) {
        word_target = wt;
    }

    public void setWordExplain(ArrayList<String> we) {
        word_explain.addAll(we);
    }

    public void addExplain(String s) {
        word_explain.add(s);
    }

    // getters
    public String getWordTarget() {
        return word_target;
    }

    public ArrayList<String> getWordExplain() {
        return word_explain;
    }
}