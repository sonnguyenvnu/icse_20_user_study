/** 
 * Traverse the AST until the root node is found.
 * @param node the node from where to start traversing the tree
 * @return the root node
 */
private Node getRootNode(final Node node){
  Node root=node;
  while (root.jjtGetParent() != null) {
    root=root.jjtGetParent();
  }
  return root;
}
