package demo.gorking.dfs;

public class TreeDiameter {
    private static int diameter=0;

    public static int findBinaryTreeDiameter(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftHeightTree= findBinaryTreeDiameter(root.left);
        int rightHeightTree= findBinaryTreeDiameter(root.right);
        int treeDiameter= leftHeightTree+rightHeightTree+1;
        diameter= Math.max(treeDiameter, diameter);
        return Math.max(leftHeightTree, rightHeightTree)+1;
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left= new TreeNode(2);
        root.right= new TreeNode(3);
        root.left.left= new TreeNode(4);
        root.right.left= new TreeNode(5);
        root.right.right= new TreeNode(6);

        System.out.println("Tree Diameter :"+findBinaryTreeDiameter(root));
    }
}
