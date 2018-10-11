/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.ArrayList;

/**
 *
 * @author thanh
 */
public class Test {
   
    
    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dict_manager = new DictionaryManagement(dictionary);
        
        dict_manager.insertFromFile();
        dictionary.removeWord("00-database-info");
        ArrayList<String> s = dictionary.storeTargetWord.suggestion("", true);
        System.out.println(s);
    }
}
