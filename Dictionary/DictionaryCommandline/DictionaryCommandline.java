import java.util.*;
import java.io.*;

public class DictionaryCommandline {
    private Dictionary dictionary;
    private DictionaryManagement dict_manager;
    private ArrayList <Word> listWordToPrint;
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
    public void showAllWords()
    {    
        if (listWordToPrint == null)
            listWordToPrint = new ArrayList<Word>(dictionary.getDict());
        boolean isEmpty = true;
        int N = 0;
        for (int i = 0; i < listWordToPrint.size(); i++) {
            if (listWordToPrint.get(i) == null) continue;
            for (int j = 0; j < listWordToPrint.get(i).getWordExplain().size(); j++) {
                if (isEmpty)
                {
                    isEmpty = false;
                    System.out.printf("%-6s|%-35s|%s%n%n", "No", "English", "Vietnamese");                     
                }
                System.out.printf("%-6d|%-35s|%s%n", ++N, listWordToPrint.get(i).getWordTarget(),
                        listWordToPrint.get(i).getWordExplain().get(j));
            }
        }
        if (isEmpty)
        {
            System.out.println("The are's any word in this Dictionay!!!");
        }
        listWordToPrint = null;
    }

    public void showAllWords(boolean isAlphabetOrder)
    {
        if (isAlphabetOrder) 
        {
            ArrayList <String> listWord = new ArrayList <>();   
            listWord = dictionary.getStoreTargetWord().suggestion("");
            if (listWord != null) 
            {
                listWordToPrint = new ArrayList<>();
                for(String English : listWord)
                {
                    Word EnglishWord = dictionary.findWord(English);
                    if (EnglishWord != null) listWordToPrint.add(EnglishWord);
                }
            }
        }
        this.showAllWords();
    }

    public void dictionaryBasic() throws IOException, Exception {
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