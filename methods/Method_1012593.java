private Node remove(Node node,K key){
  if (node == null)   return null;
  if (key.compareTo(node.key) < 0) {
    node.left=remove(node.left,key);
    return node;
  }
 else   if (key.compareTo(node.key) > 0) {
    node.right=remove(node.right,key);
    return node;
  }
 else {
    if (node.left == null) {
      Node rightNode=node.right;
      node.right=null;
      size--;
      return rightNode;
    }
    if (node.right == null) {
      Node leftNode=node.left;
      node.left=null;
      size--;
      return leftNode;
    }
    Node successor=minimum(node.right);
    successor.right=removeMin(node.right);
    successor.left=node.left;
    node.left=node.right=null;
    return successor;
  }
}
