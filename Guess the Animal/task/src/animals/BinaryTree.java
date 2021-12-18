package animals;

public class BinaryTree {
    private Node root;

    public void setRoot(Node node) {
        root = node;
    }

    public Node getRoot() {
        return root;
    }

    public static class Node {
        private Node left;
        private Node right;
        private String value;

        public Node(String value) {
            this.value = value;
        }

        public Node(String value, Node left, Node right) {
            this(value);
            this.left = left;
            this.right = right;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
