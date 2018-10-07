import java.util.*;

public class Word {
    private String word_target;
    private ArrayList<String> word_explain;

    public Word(String wt, String we) {
        word_explain = new ArrayList<String>();
        word_target = new String(wt);
        word_explain.add(we);
    }

    // setters
    public void setWordTarget(String wt) {
        word_target = new String(wt);
    }

    public void setWordExplain(ArrayList<String> we) {
        word_explain = new ArrayList<String>();
        word_explain.addAll(we);    
    }

    public void addExplain(String s) {
        if (word_explain == null) word_explain = new ArrayList<String>();
        word_explain.add(s);
    }

    // getters
    public String getWordTarget() {
        return new String(word_target);
    }

    public ArrayList<String> getWordExplain() {
        if (word_explain == null) return null;
        ArrayList <String> result = new ArrayList<String>();
        for(String s : word_explain)
        {
            result.add(s);
        }
        return result;
    }
}
