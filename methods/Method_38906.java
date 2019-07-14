/** 
 * Walks over the child notes, maintaining the tree order and not using recursion.
 */
protected void walkDescendantsIteratively(final LinkedList<Node> nodes,final CssSelector cssSelector,final List<Node> result){
  while (!nodes.isEmpty()) {
    Node node=nodes.removeFirst();
    selectAndAdd(node,cssSelector,result);
    int childCount=node.getChildNodesCount();
    for (int i=childCount - 1; i >= 0; i--) {
      nodes.addFirst(node.getChild(i));
    }
  }
}
