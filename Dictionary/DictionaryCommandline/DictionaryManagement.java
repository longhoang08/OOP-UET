import java.util.*;
import java.io.*;

public class DictionaryManagement {
    private Dictionary dictionary;

    // constructors
    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary d) {
        dictionary = d;
    }

    /*
     * end constructors
     * 
     * ----------------- METHODS ------------------------------------------------
     */

    /**
     *
     * @throws IOException
     */

    public boolean validWord(String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }

    public void insertFromCommandline() throws IOException, Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = -1;
        System.out.println("Enter number of words :");
        while (m <= 0)
        {
            try
            {
                m = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e)
            {
                // do nothing
            }
            if (m <= 0) 
            {
                System.out.println("Sorry. You must enter a positive integer number!!!");
                System.out.println("Please input again!!!");
            }
        }
        while (br.ready()) {
            br.readLine();
        }

        for (int i = 1; i <= m; i++) {

            System.out.println("Enter target word: ");
            String English = br.readLine();
            while(!validWord(English))
            {
                System.out.println("Sorry. You must input an English Word with anphabet character!!!");
                System.out.println("Please input again!!!");
                English = br.readLine();
            }
            System.out.println("Enter explain word: ");
            String Vietnamese = br.readLine();
            dictionary.insertWord(English, Vietnamese);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void dictionaryLookup() throws IOException {
        System.out.println("Enter the word you want lookup:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String English = br.readLine();
        ArrayList<String> check = dictionary.searchWord(English);
        if (check == null) {
            System.out.println("Sorry, We did not find your word in our Dictionary");
        } else {
            String add = "";
            if (check.size() > 1) add = "s";
            System.out.println("We found " + check.size() + " result" + add);
            for (int i = 0; i < check.size(); i++) {
                System.out.println(check.get(i));
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void insertFromFile() throws IOException {
        FileReader fr = new FileReader("dictionaries.txt");
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            String lineWord = br.readLine();
            String[] parts = lineWord.split("\\t");
            if (parts.length == 2) {
                dictionary.insertWord(parts[0], parts[1]);
            } else {
                // there are no word in this line
            }
        }
        fr.close();
    }

    /**
     *
     * @throws IOException
     */
    public void deleteWord() throws IOException {
        System.out.println("Enter the TARGET word you want to delete:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String English = br.readLine();
        ArrayList<String> check = dictionary.searchWord(English);
        if (check == null) {
            System.out.println("Sorry, We did not find your word in our Dictionary");
        } else {
            String add = "";
            if (check.size() > 1) add = "s";
            System.out.println("We found " + check.size() + " result" + add);
            for (int i = 0; i < check.size(); i++) {
                System.out.println(check.get(i));
            }
            System.out.println("Select an explain word to be deleted above!");
            String Vietnamese = br.readLine();
            boolean x = dictionary.removeWord(English, Vietnamese);
            if (x) {
                System.out.println("Delete successful!");
            } else {
                System.out.println("Failed to delete, may be your confirm is not legal!");
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void editWord() throws IOException {
        System.out.println("Enter the TARGET word you want to edit:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String EnglishBefore = br.readLine();
        ArrayList<String> check = dictionary.searchWord(EnglishBefore);
        if (check == null) {
            System.out.println("Sorry, We did not find your word in our Dictionary");
        } else {
            System.out.println("We found " + check.size() + " result(s): ");
            for (int i = 0; i < check.size(); i++) {
                System.out.println(check.get(i));
            }
            System.out.println("Select an explain word to be edited above!");
            String VietnameseBefore = br.readLine();
            System.out.println("Enter new word target: ");
            String EnglishAfter = br.readLine();
            System.out.println("Enter new word explain: ");
            String VietnameseAfter = br.readLine();
            boolean x = dictionary.editWord(EnglishBefore, VietnameseBefore, EnglishAfter, VietnameseAfter);
            if (x) {
                System.out.println("Edit successful!");
            } else {
                System.out.println("Failed to edit, may be your confirm is not legal!");
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void dictionaryExportToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("dictionariesUpdated.txt"));
        ArrayList<Word> refToDict = dictionary.getDict();
        for (int i = 0; i < refToDict.size(); i++) {
            ArrayList<String> refToWordExplain = refToDict.get(i).getWordExplain();
            for (int j = 0; j < refToWordExplain.size(); j++) {
                String word_target = refToDict.get(i).getWordTarget();
                String word_explain = refToDict.get(i).getWordExplain().get(j);
                writer.write(word_target + "\t" + word_explain);
                writer.newLine();
            }
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dict_manager = new DictionaryManagement(dictionary);
        dict_manager.insertFromFile();
        System.out.println(dict_manager.dictionary.getDict().get(0).getWordTarget());

    }

}