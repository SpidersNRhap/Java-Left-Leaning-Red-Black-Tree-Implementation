public class LLRB {
    private class LLRBNode {
        private int value;
        private boolean isRed;
        private LLRBNode left, right;
        
        public LLRBNode(int value, boolean isRed, LLRBNode left, LLRBNode right) {
            this.value = value;
            this.isRed = isRed;
            this.left = left;
            this.right = right;
        }

        public LLRBNode(int value) {
            this(value, true, null, null);
        }
    }

    private LLRBNode overallRoot;

    public LLRB(int[] i) {
        addAll(i);
    }

    public void inOrderTraversal() {
        inOrderTraversal(overallRoot);
    }

    private void inOrderTraversal(LLRBNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.value + " ");
            inOrderTraversal(root.right);
        }
    }

    private LLRBNode rotateLeft(LLRBNode root) {
        LLRBNode temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }

    private LLRBNode rotateRight(LLRBNode root) {
        LLRBNode temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    private void swap(LLRBNode root) {
        root.isRed = !root.isRed;
        root.left.isRed = false;
        root.right.isRed = false;
    }

    public void addAll(int[] r) {
        for (int i : r) {
            add(i);
        }
    }

    public void add(int n) {
        overallRoot = add(overallRoot, n);
        if (isRed(overallRoot)) {
            overallRoot.isRed = false;
        }
    }

    private boolean isRed(LLRBNode root) {
        if (root == null) {
            return false;
        }
        return root.isRed;
    }

    private void swapColors(LLRBNode node1, LLRBNode node2) {
        boolean temp = node1.isRed;
        node1.isRed = isRed(node2);
        if (node2 != null) {
            node2.isRed = temp;
        }
    }

    private LLRBNode add(LLRBNode root, int n) {
        if (root == null) {
            return new LLRBNode(n);
        }
        if (n < root.value) {
            root.left = add(root.left, n);
        } else if (n > root.value) {
            root.right = add(root.right, n);
        } else {
            return root;
        }
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
            swapColors(root, root.left);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
            swapColors(root, root.right);
        }
        if (isRed(root.left) && isRed(root.right)) {
            swap(root);
        }
        return root;
    }
}