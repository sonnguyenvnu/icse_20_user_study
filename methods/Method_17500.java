/** 
 * Removes the entry. 
 */
private void evictEntry(Node node){
  data.remove(node.key);
  node.remove();
  if (node.freq.isEmpty()) {
    node.freq.remove();
  }
}
