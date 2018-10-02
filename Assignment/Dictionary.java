import java.util.*;
import java.io.*;

public class Dictionary {
    private ArrayList<Word> Dict;
    private Trie storeTargetWord;

    // Constructors
    public Dictionary() {
        Dict = new ArrayList<Word>();
        storeTargetWord = new Trie();
    }

    public ArrayList<Word> getDict() {
        return Dict;
    }

    public void insertWord(String English, String Vietnamese) {
        int check = storeTargetWord.search(English);
        if (check == -1) {
            Word new_word = new Word(English, Vietnamese);
            Dict.add(new_word);
            storeTargetWord.insert(English, Dict.size() - 1);
        } else {
            Word w = Dict.get(check);
            ArrayList<String> al = w.getWordExplain();
            if(!al.contains(Vietnamese)) {
                al.add(Vietnamese);
            }
        }
    }

    public ArrayList<String> searchWord(String English) {
        int check = storeTargetWord.search(English);
        if (check == -1) {
            return null;
        } else {
            return Dict.get(check).getWordExplain();
        }
    }

    public boolean removeWord(String English, String Vietnamese) {
        int check = storeTargetWord.search(English);
        if (check != -1) {
            ArrayList<String> explainWords = Dict.get(check).getWordExplain();
            if (explainWords.contains(Vietnamese)) {
                explainWords.remove(Vietnamese);
                if (explainWords.size() == 0) {
                    storeTargetWord.remove(English);
                }
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean editWord(String EnglishBefore, String VietnameseBefore, String EnglishAfter,
            String VietnameseAfter) {
        boolean check = removeWord(EnglishBefore, VietnameseBefore);
        if (check) {
            insertWord(EnglishAfter, VietnameseAfter);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> searcher(String s) {
        ArrayList<String> suggestions = storeTargetWord.suggestion(s);
        int selfCheck = storeTargetWord.search(s);
        if (selfCheck != -1) {
            suggestions.add(s);
        }
        return suggestions;
    }

}
