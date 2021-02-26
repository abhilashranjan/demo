package demo.abhilash.algo.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PermutationSubSet {
    public static List<List<Integer>> findPermutationSubset(int[] arr){
        List<List<Integer>> result= new ArrayList<List<Integer>>();
        Queue<List<Integer>> permutations= new LinkedList<List<Integer>>();
        permutations.add(new ArrayList<Integer>());
        for(int currentNum:arr){
            int n= permutations.size();
            for(int i=0; i<n; i++){
                List<Integer> oldPermutation= permutations.poll();
                for(int j=0; j<=oldPermutation.size(); j++){
                    List<Integer> newPermutation= new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, currentNum);
                    if(newPermutation.size()== arr.length){
                        result.add(newPermutation);
                    }else{
                        permutations.add(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Permutation of :"+findPermutationSubset(new int[]{1, 3, 5}));
    }
}
