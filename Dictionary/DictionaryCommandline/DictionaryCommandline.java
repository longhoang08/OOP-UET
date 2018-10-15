import java.util.*;
import java.io.*;

public class DictionaryCommandline {
    private Dictionary dictionary;
    private DictionaryManagement dict_manager;

    // Constructor
    public DictionaryCommandline(Dictionary dictionary) {
        this.dictionary = dictionary;
        dict_manager = new DictionaryManagement(dictionary);
    }

    public DictionaryCommandline(Dictionary dictionary, DictionaryManagement dict_manager)
    {
        this.dictionary = dictionary;
        this.dict_manager = dict_manager;
    }
    // End constructor

    // ------------------- METHODS ---------------------------------------------
    public void showAllWords(ArrayList <Word> Dict_copy) 
    {    
        System.out.printf("%-6s|%-35s|%s%n%n", "No", "English", "Vietnamese");
        int N = 1;
        for (int i = 0; i < Dict_copy.size(); i++) {
            if (Dict_copy.get(i) == null) continue;
            for (int j = 0; j < Dict_copy.get(i).getWordExplain().size(); j++) {
                System.out.printf("%-6d|%-35s|%s%n", N++, Dict_copy.get(i).getWordTarget(),
                        Dict_copy.get(i).getWordExplain().get(j));
            }
        }
    }

    public void showAllWords(boolean isAlphabetOrder)
    {
        ArrayList<Word> Dict_copy;
        if (!isAlphabetOrder) 
        {
            Dict_copy = new ArrayList<Word>(dictionary.getDict());
            showAllWords(Dict_copy);
        }
        else
        {
            Dict_copy = AllWord();
        }
        
    }

    public void dictionaryBasic() throws IOException, Exception {
        dict_manager.insertFromCommandline();
        showAllWords();
    }

    // creating an menu
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
                String add = "";
                 if (results.size() > 1) add = "s";
                System.out.println("We found " + results.size() + " result" + add);
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }
            }
        }
    }
}