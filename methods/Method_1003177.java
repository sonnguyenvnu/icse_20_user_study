@Override public boolean next(){
  if (beforeFirst) {
    beforeFirst=false;
    if (node == null) {
      return false;
    }
    if (first != null && tree.compareRows(node.row,first) < 0) {
      node=next(node);
    }
  }
 else {
    node=next(node);
  }
  if (node != null && last != null) {
    if (tree.compareRows(node.row,last) > 0) {
      node=null;
    }
  }
  return node != null;
}
