public int sum(String prefix){
  Node cur=root;
  for (int i=0; i < prefix.length(); i++) {
    char c=prefix.charAt(i);
    if (cur.next.get(c) == null)     return 0;
    cur=cur.next.get(c);
  }
  return sum(cur);
}
