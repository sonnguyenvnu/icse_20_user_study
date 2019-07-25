public boolean contains(String word){
  Node cur=root;
  for (int i=0; i < word.length(); i++) {
    char c=word.charAt(i);
    if (cur.next[c - 'a'] == null)     return false;
    cur=cur.next[c - 'a'];
  }
  return cur.isWord;
}
