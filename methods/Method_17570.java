/** 
 * Removes the node from the table and adds the index to the free list. 
 */
private void removeFromTable(Node node){
  int last=data.size() - 1;
  table[node.index]=table[last];
  table[node.index].index=node.index;
  table[last]=null;
}
