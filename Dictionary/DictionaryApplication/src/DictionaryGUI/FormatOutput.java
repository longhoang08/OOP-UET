package DictionaryGUI;
import java.util.*;
import java.io.*;

public class FormatOutput 
{
    public static final String firstString[] = new String[] {"<ul><li><font color='#cc0000'><b>", "</i><br/><ul><li><b><i>"};
    
    public static String caculateNewVnm(String vie)
    {

        String result = "";
        ArrayList <Integer> listIndex = new ArrayList<>();
        for(int i = 0; i < vie.length(); i++)
        {
            if (vie.charAt(i) == '<' || vie.charAt(i) == '>') 
            {
                listIndex.add(i);
            }
        }
        if (listIndex.size() != 4) return result;
        if (listIndex.get(0) != 0)
        {
            result = vie.substring(0, listIndex.get(1));
        }
        else
        {
            result = vie.substring(listIndex.get(1) + 1, listIndex.get(2));
        }
        return result;
    }

    public static String caculateVietnameseMean(String vie)
    {
        String result = "";
        int n = vie.length();
        int leftIndex = 0, rightIndex = n, index = 0;
        while (index < n)
        {
            for(int i = index; i < n; i++)
                if (vie.charAt(i) == '<')
                {
                    rightIndex = i; 
                    break;
                }
            if (rightIndex - leftIndex >= 2)
            {
                if (result.length() == 0) result = vie.substring(leftIndex, rightIndex);
                else
                {
                    String add = vie.substring(leftIndex, rightIndex);
                    String preAdd = " -";
                    if (add.charAt(0) == ':' || add.charAt(1) == ':') preAdd = "";
                    result += preAdd + add;
                }
            }
            if (rightIndex == n) break;
            int cnt = 1;
            leftIndex = n;
            for(int i = rightIndex + 1; i < n; i++)
            {
                if (vie.charAt(i) == '<') cnt++;
                if (vie.charAt(i) == '>') cnt--;
                if (cnt == 0 && (i == n - 1 || vie.charAt(i + 1) != '<')) 
                {
                    leftIndex = i + 1;
                    break;
                } 
               
            }
            index = leftIndex;
            rightIndex = n;
        }
        return result;
    }

    public static String formatLine(String lineWord) throws IOException
    {
        String[] parts = lineWord.split("\\t");
        if (parts.length != 2)
        {
            return null;
        }
        String eng = parts[0];
        String vie = parts[1];
        String result = caculateNewVnm(vie);
        if (result.equals("") == false)
        {
            result = eng + "\t" + result;
            return result;
        }
        int pos = -1;
        for(int i = 0; i < firstString.length; i++)
        {
            int temp = vie.indexOf(firstString[i]);
            if (temp != -1) temp += firstString[i].length();
            if (pos == -1) pos = temp;
            else if (pos != -1 && temp != -1)
                pos = Math.min(pos, temp);
        }
        if (pos == -1 || eng.indexOf("=") != -1)
        {
            return eng + "\t" + eng;
        }
        while (vie.charAt(pos) == ' ') ++pos;
        vie = vie.substring(pos, vie.length());
        return eng + "\t" + caculateVietnameseMean(vie);
    }
}
