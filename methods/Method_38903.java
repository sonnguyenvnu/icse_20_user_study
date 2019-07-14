/** 
 * Process selectors and keep adding results.
 */
protected void processSelectors(final List<Node> results,final List<CssSelector> selectors){
  List<Node> selectedNodes=select(rootNode,selectors);
  for (  Node selectedNode : selectedNodes) {
    if (!results.contains(selectedNode)) {
      results.add(selectedNode);
    }
  }
}
