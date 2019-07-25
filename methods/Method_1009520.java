public int find(String prefix){
  TrieNode curr=root;
  for (int i=0; i < prefix.length(); i++) {
    Character ch=prefix.charAt(i);
    curr=curr.getChild(ch);
    if (curr == null) {
      return 0;
    }
  }
  return curr.size;
}
