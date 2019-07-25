public boolean search(String word){
  Node cur=root;
  for (int i=0; i < word.length(); i++) {
    char c=word.charAt(i);
    if (cur.next.get(c) == null)     return false;
    cur=cur.next.get(c);
  }
  return cur.isWord;
}
