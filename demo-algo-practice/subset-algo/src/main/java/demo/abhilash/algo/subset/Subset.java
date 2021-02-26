package demo.abhilash.algo.subset;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static List<List<Integer>> findSubset(int arr[]){
        List<List<Integer>> subsets= new ArrayList<List<Integer>>();
        subsets.add(new ArrayList<Integer>());
        for(Integer currentNum:arr){
            int n= subsets.size();
            for(int i=0; i<n; i++){
                List<Integer> set= new ArrayList<Integer>(subsets.get(i));
                set.add(currentNum);
                subsets.add(set);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        System.out.println("List of subset :"+findSubset(new int[]{1,3}));
    }
}
