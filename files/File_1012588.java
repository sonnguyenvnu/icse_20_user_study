import java.util.TreeMap;

/// TrieR 是 Trie in Recursion的�?�?
/// TrieR将使用递归的方�?，实现我们在这一章所讲解的Trie的基本功能
public class TrieR {

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
    private int size;

    public TrieR(){
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的�?��?数�?
    public int getSize(){
        return size;
    }

    // �?�Trie中添加一个新的�?��?word
    public void add(String word){

        add(root, word, 0);
    }

    // �?�以node为根的Trie中添加word[index...end), 递归算法
    private void add(Node node, String word, int index){

        if(index == word.length()){
            if(!node.isWord){
                node.isWord = true;
                size ++;
            }
            return;
        }

        char c = word.charAt(index);
        if(node.next.get(c) == null)
            node.next.put(c, new Node());
        add(node.next.get(c), word, index + 1);
    }

    // 查询�?��?word是�?�在Trie中
    public boolean contains(String word){
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
    public boolean isPrefix(String prefix){
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

    // 删除word, 返回是�?�删除�?功, 递归算法
    public boolean remove(String word){
        if(word.equals(""))
            return false;
        return remove(root, word, 0);
    }

    // 在以Node为根的Trie中删除�?��?word[index...end),返回是�?�删除�?功, 递归算法
    private boolean remove(Node node, String word, int index){

        if(index == word.length()){
            if(!node.isWord)
                return false;
            node.isWord = false;
            size --;
            return true;
        }

        char c = word.charAt(index);
        if(!node.next.containsKey(c))
            return false;

        boolean ret = remove(node.next.get(c), word, index + 1);
        Node nextNode = node.next.get(c);
        if(!nextNode.isWord && nextNode.next.size() == 0)
            node.next.remove(word.charAt(index));
        return ret;
    }
}
