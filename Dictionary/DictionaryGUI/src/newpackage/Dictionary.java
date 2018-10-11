package newpackage;
import java.util.*;

public class Dictionary {
    private ArrayList<Word> Dict;
    public Trie storeTargetWord;

    // Constructors
    public Dictionary() {
        Dict = new ArrayList<Word>();
        storeTargetWord = new Trie();
    }

    public ArrayList<Word> getDict() {
        return Dict;
    }

    public boolean insertWord(String English, String Vietnamese) {
        int check = storeTargetWord.search(English);
        if (check == -1) {
            Word new_word = new Word(English, Vietnamese);
            Dict.add(new_word);
            storeTargetWord.insert(English, Dict.size() - 1);
            return true;
        }
        else
            return false;
    }

    public String searchWord(String English) {
        int check = storeTargetWord.search(English);
        if (check == -1) {
            return null;
        } else {
            return Dict.get(check).getWordExplain();
        }
    }

    public boolean removeWord(String English) {
        int check = storeTargetWord.remove(English);
        if (check != -1) {
            
            return true;
        } else {
            return false;
        }

    }

    public boolean editWord(String EnglishBefore, String EnglishAfter,
            String VietnameseAfter) {
        boolean check = removeWord(EnglishBefore);
        if (check) {
            insertWord(EnglishAfter, VietnameseAfter);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> searcher(String s, boolean permission) {
        ArrayList<String> suggestions = storeTargetWord.suggestion(s, permission);
        int selfCheck = storeTargetWord.search(s);
        if (selfCheck != -1) {
            suggestions.add(s);
        }
        return suggestions;
    }


}
