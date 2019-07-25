import java.util.TreeMap;

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

    // èŽ·å¾—Trieä¸­å­˜å‚¨çš„å?•è¯?æ•°é‡?
    public int getSize(){
        return size;
    }

    // å?‘Trieä¸­æ·»åŠ ä¸€ä¸ªæ–°çš„å?•è¯?word
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

    // æŸ¥è¯¢å?•è¯?wordæ˜¯å?¦åœ¨Trieä¸­
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

    // æŸ¥è¯¢æ˜¯å?¦åœ¨Trieä¸­æœ‰å?•è¯?ä»¥prefixä¸ºå‰?ç¼€
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
}
