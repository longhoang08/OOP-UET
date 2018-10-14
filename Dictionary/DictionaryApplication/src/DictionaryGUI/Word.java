package DictionaryGUI;
import java.util.*;

public class Word {
    private String word_target;
    private String word_explain;

    public Word(String wt, String we) {
        word_explain = we;
        word_target = wt;
    }
    public Word(Word w) {
        word_explain = w.word_explain;
        word_target = w.word_target;
    }
    // setters
    public void copy(Word w) {
        word_target = w.word_target;
        word_explain = w.word_explain;
    }
    public void setWordTarget(String wt) {
        word_target = wt;
    }

    public void setWordExplain(String we) {
        word_explain = we;
    }

    // getters
    public String getWordTarget() {
        return word_target;
    }

    public String getWordExplain() {
        return word_explain;
    }
}
