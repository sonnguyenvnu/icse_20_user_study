public Set<String> keySet(){
  Set<String> keySet=new LinkedHashSet<String>();
  for (  Map.Entry<String,Byte> entry : trie.entrySet()) {
    keySet.add(entry.getKey());
  }
  return keySet;
}
