package DictionaryGUI;
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

    /**
     *
     * @throws IOException
     */
    public void insertFromFile() throws IOException {
        File fileDir = new File("dictionaries.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
        while (br.ready()) {
            String lineWord = br.readLine();
            String[] parts = lineWord.split("\\t");
            if (parts.length == 2) {
                dictionary.insertWord(parts[0], parts[1]);
            }
        }
        br.close();    
    }
    public void dictionaryExportToFile() throws IOException{
        File fileDir = new File("DictionariesUpdated.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));
        ArrayList<Word> dict = dictionary.getDict();
        for(Word w : dict) {
            String en = w.getWordTarget();
            String vi = w.getWordExplain();
            writer.write(en + "\t" + "<html>" + vi + "</html>");
            writer.newLine();
        }
        writer.close();
    }

}