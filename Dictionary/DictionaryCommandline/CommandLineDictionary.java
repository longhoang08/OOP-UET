import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineDictionary
{
    Dictionary dictionary;
    DictionaryManagement dictionaryManager;
    DictionaryCommandline cmdDictionary;

    // clear the screen
    private static void cls() throws IOException, InterruptedException 
    { 
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args)  throws Exception
    {
        CommandLineDictionary cmd = new CommandLineDictionary();
        cmd.dictionary = new  Dictionary();
        cmd.dictionaryManager = new DictionaryManagement(cmd.dictionary);
        cmd.cmdDictionary = new DictionaryCommandline(cmd.dictionary, cmd.dictionaryManager);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        cls();

        System.out.println("English - Vietnamese Dictionary - Command line version");
        System.out.println("Author: Long Hoang Bao - Dat Nguyen Thanh");
        System.out.println();
        System.out.println("Press enter to continue");
        br.readLine();

        cls();
        
        //creat an menu
        boolean isImport = false;
        boolean isContinue = true;
        
        do
        {
            System.out.println("Dictionary Command Line Menu:");
            System.out.println("1: Import data from file, you can choose this function only once");
            System.out.println("2: Show all world in this dictionary.");
            System.out.println("3: Insert data from command line");
            System.out.println("4: Search an word by command line.");
            System.out.println("5: Delete a english word in this dictionary.");
            System.out.println("6: Input an prefix s to command line, find all english world in this dictionary which have same prefix with s");
            System.out.println("7: Export this dictionay to file");
            System.out.println("8: Exit");
            System.out.println("Please select fuction [1-8]!");
            
            int select;
            try
            {
                select = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e)
            {
                cls();
                System.out.println("Error!!!. Wrong input formart. You should input a number in range [1,8]!");
                System.out.println("Please input again!!!");
                System.out.println();
                continue;
            }
        
            cls();
            boolean isPress = true;
            switch (select)
            {
                case 1: 
                {
                    if (isImport)
                    {
                        System.out.println("Error!! You have already import data from file!!!");
                    }
                    else
                    {
                        isImport = cmd.dictionaryManager.insertFromFile();
                        if (isImport)
                            System.out.println("Import successed!!!");
                    }
                    break;
                }

                case 2:
                {
                    System.out.println("Would you like to sort results in abphabet order? (y/n)");
                    boolean isAlphabetOrder = false;
                    boolean ok = true;
                    while(ok)
                    {
                        ok = false;
                        String choose = br.readLine();
                        if (choose.equals("y") || choose.equals("Y") || choose.equals("yes") || choose.equals("Yes"))
                            isAlphabetOrder = true;
                        else if (!(choose.equals("n") || choose.equals("N") || choose.equals("no") || choose.equals("No")))
                        {
                            cls();
                            System.out.println("Sorry. You must choose Yes (y) or No (n)!!!");
                            System.out.println("Please choose again!!!!");
                            ok = true;
                        }
                    }
                    cls();
                    cmd.cmdDictionary.showAllWords(isAlphabetOrder);
                    break;
                }

                case 3:
                {
                    cmd.dictionaryManager.insertFromCommandline();
                    break;
                }

                case 4:
                {
                    cmd.dictionaryManager.dictionaryLookup();   
                    break;
                }

                case 5:
                {
                    cmd.dictionaryManager.deleteWord();
                    break;
                }

                case 6:
                {
                    cmd.cmdDictionary.dictionarySearcher();
                    break;
                }

                case 7:
                {
                    cmd.dictionaryManager.dictionaryExportToFile();
                    System.out.println("Export completed");
                    break;
                }
                
                case 8:
                {
                    System.out.println("Goodbye!!!");
                    isContinue = false;
                    break;
                }

                default:
                {
                    isPress = false;
                    System.out.println("Error!!!. You should input a number in range [1,8]!");
                    System.out.println("Please input again!!!");
                }
            }
            if (isContinue && isPress)
            {
                System.out.println("Press enter to continue");
                br.readLine();
                cls();
            }
            else if (isContinue)
            {
                isPress  = true;
                System.out.println();
            }
        } while (isContinue);
    }
}