package newpackage;
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
        File fileDir = new File("src\\dictionaries.txt");
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

}