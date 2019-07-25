/// 208. Implement Trie (Prefix Tree)
/// https://leetcode.com/problems/implement-trie-prefix-tree/description/

import java.util.TreeMap;

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

    // å?‘Trieä¸­æ·»åŠ ä¸€ä¸ªæ–°çš„å?•è¯?word
    public void insert(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    // æŸ¥è¯¢å?•è¯?wordæ˜¯å?¦åœ¨Trieä¸­
    public boolean search(String word){

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
    public boolean startsWith(String isPrefix){

        Node cur = root;
        for(int i = 0 ; i < isPrefix.length() ; i ++){
            char c = isPrefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
