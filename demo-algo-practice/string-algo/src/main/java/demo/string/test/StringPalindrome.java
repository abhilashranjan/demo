package demo.string.test;

import java.util.Optional;

public class StringPalindrome {

    public static String reverseString(String str){
        if(str.isEmpty()){
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        String palindrome="ABCBA";
        if(palindrome.equalsIgnoreCase(reverseString(palindrome)))
            System.out.println("palindrome");
        else
            System.out.println("not palindrome");
    }
}
