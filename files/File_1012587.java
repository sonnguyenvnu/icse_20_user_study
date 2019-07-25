import java.util.TreeMap;
import java.util.Stack;


public class Trie {

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

    public Trie(){
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的�?��?数�?
    public int getSize(){
        return size;
    }

    // �?�Trie中添加一个新的�?��?word
    public void add(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    // 查询�?��?word是�?�在Trie中
    public boolean contains(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询是�?�在Trie中有�?��?以prefix为�?缀
    public boolean isPrefix(String prefix){

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }

    // 删除word, 返回是�?�删除�?功
    public boolean remove(String word){

        // 将�?�索沿路的节点放入栈中
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for(int i = 0; i < word.length(); i ++){
            if(!stack.peek().next.containsKey(word.charAt(i)))
                return false;
            stack.push(stack.peek().next.get(word.charAt(i)));
        }

        if(!stack.peek().isWord)
            return false;

        // 将该�?��?结尾isWord置空
        stack.peek().isWord = false;
        size --;

        // 如果�?��?最�?�一个字�?的节点的next�?�空，
        // 说明trie中还存储了其他以该�?��?为�?缀的�?��?，直接返回
        if(stack.peek().next.size() > 0)
            return true;
        else
            stack.pop();

        // 自底�?�上删除
        for(int i = word.length() - 1; i >= 0; i --){
            stack.peek().next.remove(word.charAt(i));
            // 如果一个节点的isWord为true，或者是其他�?��?的�?缀，则直接返回
            if(stack.peek().isWord || stack.peek().next.size() > 0)
                return true;
            stack.pop();
        }
        return true;
    }
}

