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
        private Node no;
        private Node yes;
        private String value;

        public Node() {}

        public Node(String value) {
            this.value = value;
        }

        public Node(String value, Node no, Node yes) {
            this(value);
            this.no = no;
            this.yes = yes;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNo() {
            return no;
        }

        public Node getYes() {
            return yes;
        }

        public void setNo(Node no) {
            this.no = no;
        }

        public void setYes(Node yes) {
            this.yes = yes;
        }
    }
}
