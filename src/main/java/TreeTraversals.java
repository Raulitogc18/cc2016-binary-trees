import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class TreeTraversals {

    public static <V> void preorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }

        // root
        result.add(node.getValue());

        // left
        preorder(node.getLeft(), result);

        // right
        preorder(node.getRight(), result);
    }

    public static <V> void inorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }

        // left
        inorder(node.getLeft(), result);

        // root
        result.add(node.getValue());

        // right
        inorder(node.getRight(), result);
    }

    public static <V> void postorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }

        // left
        postorder(node.getLeft(), result);

        // right
        postorder(node.getRight(), result);

        // root
        result.add(node.getValue());
    }

    public static <V> void levelorder(BinaryTree<V> node, List<V> result) {
        if (node == null) {
            return;
        }

        Queue<BinaryTree<V>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            BinaryTree<V> current = queue.poll();
            result.add(current.getValue());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }
}