package demo.gorking.dfs;

public class TreeDiameterMaxSum {
    private static int maxSumDiameter =Integer.MIN_VALUE;

    public static int findBinaryTreeDiameter(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftHeightMaxSumTree= findBinaryTreeDiameter(root.left);
        int rightHeightMaxSumTree= findBinaryTreeDiameter(root.right);

        leftHeightMaxSumTree=Math.max(leftHeightMaxSumTree, 0);
        rightHeightMaxSumTree=Math.max(rightHeightMaxSumTree, 0);

        int localMaxSumTreeDiameter= leftHeightMaxSumTree+rightHeightMaxSumTree+ root.val;
        maxSumDiameter = Math.max(localMaxSumTreeDiameter, maxSumDiameter);
        return Math.max(leftHeightMaxSumTree, rightHeightMaxSumTree)+ root.val;
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left= new TreeNode(2);
        root.right= new TreeNode(3);
        root.left.left= new TreeNode(4);
        root.right.left= new TreeNode(5);
        root.right.right= new TreeNode(6);

        System.out.println("Tree Max Diameter Sum :"+findBinaryTreeDiameter(root));
    }
}
