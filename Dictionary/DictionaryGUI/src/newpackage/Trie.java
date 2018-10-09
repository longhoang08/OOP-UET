package newpackage;
import java.util.*;

class TrieNode {
    static final int ALL_CHAR = 95;
    private TrieNode[] children;
    private int idArray;

    public TrieNode() {
        children = new TrieNode[95];
        idArray = -1;
        for (int i = 0; i < ALL_CHAR; i++) {
            children[i] = null;
        }
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public int getIdArray() {
        return idArray;
    }

    public void setIdArray(int i) {
        idArray = i;
    }

}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Trie(TrieNode root) {
        this.root = root;
    }

    public TrieNode getRoot() {
        return root;
    }

    public void resetId(String key, int id) {

        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if(index>94) continue;
            pointer = pointer.getChildren()[index];
            
        }
        pointer.setIdArray(id);
    }

    public void insert(String key, int indexOfArray) {

        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if(index>94) continue;
            if (pointer.getChildren()[index] == null) {
                pointer.getChildren()[index] = new TrieNode();
            }
            pointer = pointer.getChildren()[index];
        }
        pointer.setIdArray(indexOfArray);
    }

    public int search(String key) {
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if(index>94) continue;
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else {
                return -1;
            }
        }
        if (pointer != null && pointer.getIdArray() >= 0) {
            return pointer.getIdArray();
        } else {
            return -1; // Can't find anything
        }
    }

    public int remove(String key) { // set -1 for inArray and return the indexOfArrayList

        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = (int) key.charAt(i) - ' ';
            if(index>94) continue;
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else
                return -1;
        }
        if (pointer != null && pointer.getIdArray() >= 0) {
            int temp = pointer.getIdArray(); // remove from Trie
            pointer.setIdArray(-1);
            return temp;
        } else
            return -1; // Can't find anything
    }

    public ArrayList<String> suggestion(String s) {
        
        
        ArrayList<String> possibilities = new ArrayList<String>();
        if (s.length() == 0)
            return possibilities;
        String key = s.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = s.charAt(i) - ' ';
            if(index>94) continue;
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else {
               return possibilities;
            }
        }
        for (int i = 0; i < TrieNode.ALL_CHAR; i++) {
            if (pointer.getChildren()[i] != null) {
                char addition_char = (char) ((int) (' ') + i);
                if (pointer.getChildren()[i].getIdArray() != -1) {
                    possibilities.add(s + addition_char);
                }
                ArrayList<String> additionalArrayList = suggestion(s + addition_char);
                
              
                     possibilities.addAll(additionalArrayList);
                     
                
  
            }
        }

        return possibilities;
    }

   
}