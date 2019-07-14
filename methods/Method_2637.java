@Override public String lastKey(){
  MutableDoubleArrayTrieInteger.KeyValuePair iterator=trie.iterator();
  while (iterator.hasNext()) {
    iterator.next();
  }
  return iterator.key();
}
