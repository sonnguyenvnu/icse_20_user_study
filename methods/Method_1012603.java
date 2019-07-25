private Node remove(Node node,K key){
  if (node == null)   return null;
  Node retNode;
  if (key.compareTo(node.key) < 0) {
    node.left=remove(node.left,key);
    retNode=node;
  }
 else   if (key.compareTo(node.key) > 0) {
    node.right=remove(node.right,key);
    retNode=node;
  }
 else {
    if (node.left == null) {
      Node rightNode=node.right;
      node.right=null;
      size--;
      retNode=rightNode;
    }
 else     if (node.right == null) {
      Node leftNode=node.left;
      node.left=null;
      size--;
      retNode=leftNode;
    }
 else {
      Node successor=minimum(node.right);
      successor.right=remove(node.right,successor.key);
      successor.left=node.left;
      node.left=node.right=null;
      retNode=successor;
    }
  }
  if (retNode == null)   return null;
  retNode.height=1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
  int balanceFactor=getBalanceFactor(retNode);
  if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)   return rightRotate(retNode);
  if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)   return leftRotate(retNode);
  if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
    retNode.left=leftRotate(retNode.left);
    return rightRotate(retNode);
  }
  if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
    retNode.right=rightRotate(retNode.right);
    return leftRotate(retNode);
  }
  return retNode;
}
