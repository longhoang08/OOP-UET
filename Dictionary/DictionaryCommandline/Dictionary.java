import java.util.*;
import java.io.*;

public class Dictionary {
    private ArrayList<Word> Dict;
    private Trie storeTargetWord;
    // private ArrayList<Integer> emptyId;

    /**
     * @return the storeTargetWord
     */
    public Trie getStoreTargetWord() {
        return storeTargetWord;
    }

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
            ArrayList<String> al = Dict.get(check).getWordExplain();
            for (String s : al) {
                if (Vietnamese.equals(s))
                    return;
            }
            al.add(Vietnamese);
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

    public Word findWord(String English) {
        int check = storeTargetWord.search(English);
        if (check == -1) {
            return null;
        } else {
            return Dict.get(check);
        }
    }

    public boolean removeWord(String English, String Vietnamese) {
        int check = storeTargetWord.search(English);
        if (check != -1) {
            ArrayList<String> explainWords = Dict.get(check).getWordExplain();
            if (explainWords.contains(Vietnamese)) {
                explainWords.remove(Vietnamese);
                if (explainWords.size() == 0) {
                    int end = Dict.size();

                    Word remove = Dict.get(check);
                    Word lastWord = Dict.get(end - 1);
                    Word temp = new Word();

                    temp.copy(lastWord);
                    lastWord.copy(remove);
                    remove.copy(temp);

                    storeTargetWord.remove(English);
                    Dict.remove(Dict.get(end - 1));

                    /*
                     * BEFORE FIX BUG storeTargetWord.remove(English); Dict.remove(Dict.get(check));
                     */
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
