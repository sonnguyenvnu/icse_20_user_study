/** 
 * Removes the node from the table and adds the index to the free list. 
 */
private void removeFromTable(Node[] table,Node node){
  int last=table.length - 1;
  table[node.index]=table[last];
  table[node.index].index=node.index;
  table[last]=null;
}
