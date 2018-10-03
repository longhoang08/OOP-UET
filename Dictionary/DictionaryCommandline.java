import java.util.*;
import java.io.*;

public class DictionaryCommandline {
    private Dictionary dictionary;
    private DictionaryManagement dict_manager;

    // Constructor
    public DictionaryCommandline(Dictionary d) {
        dictionary = d;
        dict_manager = new DictionaryManagement(d);
    }
    // End constructor

    // ------------------- METHODS ---------------------------------------------
    public void showAllWords() {
        ArrayList<Word> Dict_copy = new ArrayList<Word>(dictionary.getDict());
        System.out.printf("%-6s|%-35s|%s%n%n", "No", "English", "Vietnamese");
        int N = 1;
        for (int i = 0; i < Dict_copy.size(); i++) {
            for (int j = 0; j < Dict_copy.get(i).getWordExplain().size(); j++) {
                System.out.printf("%-6d|%-35s|%s%n", N++, Dict_copy.get(i).getWordTarget(),
                        Dict_copy.get(i).getWordExplain().get(j));
            }
        }
    }

    public void dictionaryBasic() throws IOException {
        dict_manager.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        dict_manager.insertFromFile();
        showAllWords();
        dict_manager.dictionaryLookup();
    }

    public void dictionarySearcher() throws IOException {
        System.out.println("What are you looking for ?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        ArrayList<String> results = new ArrayList<String>();
        results = dictionary.searcher(s);
        if (results == null) {
            System.out.println("We can't find any suggestion for you, Sorry!");
        } else {
            if (results.size() == 0) {
                System.out.println("We can't find any suggestion for you, Sorry!");
            } else {
                System.out.println("We found " + results.size() + " result(s)");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }
            }
        }
    }
}