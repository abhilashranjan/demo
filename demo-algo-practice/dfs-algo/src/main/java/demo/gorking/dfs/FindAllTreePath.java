package demo.gorking.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindAllTreePath {

    public static List<List<Integer>> findAllPath(TreeNode root, int sum){
        List<List<Integer>> allPath= new ArrayList<List<Integer>>();
        List<Integer> currentPath= new ArrayList<Integer>();
        findRecurisvePath(root, sum, currentPath, allPath);
        return allPath;
    }
    private static void findRecurisvePath(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>>allPath){
        if(root==null){
            return;
        }
        currentPath.add(root.val);
        if(root.val==sum && root.left==null && root.right==null){
            allPath.add(new ArrayList<Integer>(currentPath));
        }else{
            findRecurisvePath(root.left, sum- root.val, currentPath, allPath);
            findRecurisvePath(root.right, sum- root.val, currentPath, allPath);
        }

        currentPath.remove(currentPath.size()-1);
    }


    public static void main(String[] args) {
        TreeNode root= new TreeNode(12);
        root.left= new TreeNode(7);
        root.right= new TreeNode(1);
        root.left.left= new TreeNode(4);
        root.right.left= new TreeNode(10);
        root.right.right= new TreeNode(5);

        List<List<Integer>> allPath= findAllPath(root, 23);
        System.out.println("Tree path with sum :"+allPath);

    }
}

