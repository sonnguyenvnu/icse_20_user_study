@Override public int idOf(String string){
  Integer id=trie.get(string);
  if (id == null) {
    if (mutable) {
      id=trie.size();
      trie.put(string,id);
    }
 else     id=UNK;
  }
  return id;
}
