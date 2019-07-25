public class Trie3 {

    private class Node{

        public boolean isWord;
        public Node[] next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new Node[26];
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie3(){
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
            if(cur.next[c-'a'] == null)
                cur.next[c-'a'] = new Node();
            cur = cur.next[c-'a'];
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
            if(cur.next[c-'a'] == null)
                return false;
            cur = cur.next[c-'a'];
        }
        return cur.isWord;
    }
}
