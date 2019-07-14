/** 
 * Finds nodes in the tree that matches single selector.
 */
protected void walk(final Node rootNode,final CssSelector cssSelector,final List<Node> result){
  CssSelector previousCssSelector=cssSelector.getPrevCssSelector();
  Combinator combinator=previousCssSelector != null ? previousCssSelector.getCombinator() : Combinator.DESCENDANT;
switch (combinator) {
case DESCENDANT:
    LinkedList<Node> nodes=new LinkedList<>();
  int childCount=rootNode.getChildNodesCount();
for (int i=0; i < childCount; i++) {
  nodes.add(rootNode.getChild(i));
}
walkDescendantsIteratively(nodes,cssSelector,result);
break;
case CHILD:
childCount=rootNode.getChildNodesCount();
for (int i=0; i < childCount; i++) {
Node node=rootNode.getChild(i);
selectAndAdd(node,cssSelector,result);
}
break;
case ADJACENT_SIBLING:
Node node=rootNode.getNextSiblingElement();
if (node != null) {
selectAndAdd(node,cssSelector,result);
}
break;
case GENERAL_SIBLING:
node=rootNode;
while (true) {
node=node.getNextSiblingElement();
if (node == null) {
break;
}
selectAndAdd(node,cssSelector,result);
}
break;
}
}
