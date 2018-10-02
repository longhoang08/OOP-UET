import java.util.*;

class TrieNode {
    static final int ALPHABET_SIZE = 26;
    private TrieNode[] children;
    private int idArray;

    public TrieNode() {
        children = new TrieNode[26];
        idArray = -1;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
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

    public void insert(String s, int indexOfArray) {
        String key = s.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (pointer.getChildren()[index] == null) {
                pointer.getChildren()[index] = new TrieNode();
            }
            pointer = pointer.getChildren()[index];
        }
        pointer.setIdArray(indexOfArray);
    }

    public int search(String s) {
        String key = s.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
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

    public int remove(String s) { // set -1 for inArray and return the indexOfArrayList
        String key = s.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = (int) key.charAt(i) - 'a';
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
        String key = s.toLowerCase();
        if(s.length()==0)
            return null;
        ArrayList<String> possibilities = new ArrayList<String>();
        
        TrieNode pointer = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else {
                return null;
            }
        }
        for (int i = 0; i < TrieNode.ALPHABET_SIZE; i++) {
            if (pointer.getChildren()[i] != null) {
                char addition_char = (char) ((int) ('a') + i);
                if (pointer.getChildren()[i].getIdArray() != -1) {
                    possibilities.add(s + addition_char);
                }
                ArrayList<String> additionalArrayList = suggestion(s + addition_char);
                possibilities.addAll(additionalArrayList);
            }
        }
        
        return possibilities;
    }

    public static void main(String[] args) {
        Trie test = new Trie();
        test.insert("abc", 0);
    }
}