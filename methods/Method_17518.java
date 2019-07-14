private void evictEntry(Node node){
  data.remove(node.key);
  node.remove();
}
