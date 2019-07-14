public void add(String key){
  TermFrequency termFrequency=trie.get(key);
  if (termFrequency == null) {
    termFrequency=new TermFrequency(key);
    trie.put(key,termFrequency);
  }
 else {
    termFrequency.increase();
  }
}
