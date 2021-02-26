package demo.abhilash.algo.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class PermutateArray {

    public static void main(String[] args) {
        PermutateArray pa = new PermutateArray();

        int[] arr = {10, 20, 30};

        List<ArrayList<Integer>> permute = pa.permutetion(arr);

        System.out.println("Permuations of array : [10, 20, 30] are:");
        System.out.println("=========================================");
        for (List<Integer> perm : permute) {
            System.out.println(perm);
        }

    }

    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<Integer>(), arr);
        return list;
    }

    private void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int[] arr) {

        // Base case
        if (resultList.size() == arr.length) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < arr.length; i++) {

                if (resultList.contains(arr[i])) {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(arr[i]);
                // Explore
                permuteHelper(list, resultList, arr);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }




//    Leet code
public List<ArrayList<Integer>> permutetion(int[] num) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    //start from an empty list
    result.add(new ArrayList<Integer>());

    for (int i = 0; i < num.length; i++) {
        //list of list in current iteration of the array num
        ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

        for (List<Integer> l : result) {
            // # of locations to insert is largest index + 1
            for (int j = 0; j < l.size()+1; j++) {
                // + add num[i] to different locations
                l.add(j, num[i]);

                ArrayList<Integer> temp = new ArrayList<Integer>(l);
                current.add(temp);

                //System.out.println(temp);

                // - remove num[i] add
                l.remove(j);
            }
        }

        result = new ArrayList<ArrayList<Integer>>(current);
    }

    return result;
}

}
 