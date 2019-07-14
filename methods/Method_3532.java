public AhoCorasickDoubleArrayTrieSegment loadDictionary(String... pathArray){
  trie=new AhoCorasickDoubleArrayTrie<CoreDictionary.Attribute>();
  TreeMap<String,CoreDictionary.Attribute> map=null;
  try {
    map=IOUtil.loadDictionary(pathArray);
  }
 catch (  IOException e) {
    logger.warning("??????\n" + TextUtility.exceptionToString(e));
    return this;
  }
  if (map != null && !map.isEmpty()) {
    trie.build(map);
  }
  return this;
}
