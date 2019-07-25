import java.util.TreeMap;

/// 使用Leetcode 208�?�问题测试我们实现的TrieR
public class Trie208 {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    public Trie208(){
        root = new Node();
    }

    // �?�Trie中添加一个新的�?��?word
    public void insert(String word){
        add(root, word, 0);
    }

    // �?�以node为根的Trie中添加word[index...end), 递归算法
    private void add(Node node, String word, int index){

        if(index == word.length()){
            if(!node.isWord)
                node.isWord = true;
            return;
        }

        char c = word.charAt(index);
        if(node.next.get(c) == null)
            node.next.put(c, new Node());
        add(node.next.get(c), word, index + 1);
    }

    // 查询�?��?word是�?�在Trie中
    public boolean search(String word){
        return contains(root, word, 0);
    }

    // 在以node为根的Trie中查询�?��?word[index...end)是�?�存在, 递归算法
    private boolean contains(Node node, String word, int index){

        if(index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if(node.next.get(c) == null)
            return false;

        return contains(node.next.get(c), word, index + 1);
    }

    // 查询是�?�在Trie中有�?��?以prefix为�?缀
    public boolean startsWith(String prefix){
        return isPrefix(root, prefix, 0);
    }

    // 查询在以Node为根的Trie中是�?�有�?��?以prefix[index...end)为�?缀, 递归算法
    private boolean isPrefix(Node node, String prefix, int index){

        if(index == prefix.length())
            return true;

        char c = prefix.charAt(index);
        if(node.next.get(c) == null)
            return false;

        return isPrefix(node.next.get(c), prefix, index + 1);
    }
}
