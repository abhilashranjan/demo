package demo.abhilash.algo.subset;

import java.util.ArrayList;
import java.util.List;

public class LaterCaseSubset {

    private static List<String> findLatterCaseSubset(String str){
        List<String> permutation= new ArrayList<String>();
        if(str== null){
            return permutation;
        }
        permutation.add(str);
        for(int i=0; i <str.length(); i++){
            if(Character.isLetter(str.charAt(i))){// Only process character skip others
                int n = permutation.size();
                for(int j=0; j<n; j++){
                    char[] chars= permutation.get(j).toCharArray();
                    //If current character is in upper case.
                    if(Character.isUpperCase(chars[i]))
                        chars[i]=Character.toLowerCase(chars[i]);
                    else
                        chars[i]=Character.toUpperCase(chars[i]);
                    permutation.add(String.valueOf(chars));
                }
            }
        }
        return permutation;
    }

    public static void main(String[] args) {
        System.out.println("List of permutation :- "+findLatterCaseSubset("ab52"));
    }
}
