/** 
 * ????????????????????????????
 * @param other ???
 */
public void combine(SimpleDictionary<V> other){
  if (other.trie == null) {
    logger.warning("????????");
    return;
  }
  for (  Map.Entry<String,V> entry : other.trie.entrySet()) {
    if (trie.containsKey(entry.getKey()))     continue;
    trie.put(entry.getKey(),entry.getValue());
  }
}