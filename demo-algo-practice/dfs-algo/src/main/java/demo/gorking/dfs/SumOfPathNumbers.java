package demo.gorking.dfs;

public class SumOfPathNumbers {

    public static int findRootToLeafSum(TreeNode root, int sumPath){
        if(root==null){
            return 0;
        }
        sumPath= 10* sumPath+root.val;
        if(root.left==null && root.right==null){
            return sumPath;
        }
        return findRootToLeafSum(root.left,sumPath)+findRootToLeafSum(root.right,sumPath);
    }
    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left= new TreeNode(0);
        root.right= new TreeNode(1);
        root.left.left= new TreeNode(1);
        root.right.left= new TreeNode(6);
        root.right.right= new TreeNode(5);

        System.out.println("Tree path with sum :"+findRootToLeafSum(root,0));

    }
}
