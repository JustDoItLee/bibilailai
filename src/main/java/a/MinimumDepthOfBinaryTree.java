package a;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 * 问题的本质就是让你在一幅「图」中找到从起点 start 到终点 target 的最近距离
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode a1 = new TreeNode(9);
        TreeNode a2 = new TreeNode(20);
        root.left = a1;
        root.right = a2;
        TreeNode a3 = new TreeNode(15);
        TreeNode a4 = new TreeNode(7);
        a2.left = a3;
        a2.right = a4;
        System.out.println(new MinimumDepthOfBinaryTree().minDepth(root));
    }
}
