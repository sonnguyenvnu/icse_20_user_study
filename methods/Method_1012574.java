public void insert(String word){
  Node cur=root;
  for (int i=0; i < word.length(); i++) {
    char c=word.charAt(i);
    if (cur.next.get(c) == null)     cur.next.put(c,new Node());
    cur=cur.next.get(c);
  }
  cur.isWord=true;
}
