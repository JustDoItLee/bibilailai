package a;

/* 基本的二叉树节点 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    void traverse(TreeNode node) {
        traverse(node.left);
        traverse(node.right);
    }
}
