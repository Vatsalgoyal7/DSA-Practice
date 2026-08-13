class Solution {
    static class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;

        Node() {}

        Node(char c) {
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.leftChar = c;
            this.rightChar = c;
        }
    }

    private Node[] tree;
    private char[] sChars;

    private Node merge(Node left, Node right, int leftSize, int rightSize) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // Update prefix length
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == leftSize && left.rightChar == right.leftChar) {
            res.prefixLen = leftSize + right.prefixLen;
        }

        // Update suffix length
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == rightSize && left.rightChar == right.leftChar) {
            res.suffixLen = rightSize + left.suffixLen;
        }

        // Update maximum length across boundary
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(sChars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            sChars[idx] = ch;
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        sChars = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}