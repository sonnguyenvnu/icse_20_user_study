private DataFlowNode getNodeToBreakStatement(DataFlowNode node){
  LOGGER.entering(CLASS_NAME,"getNodeToBreakStatement");
  List<DataFlowNode> bList=node.getFlow();
  int findEnds=1;
  int index=bList.indexOf(node);
  for (; index < bList.size() - 2; index++) {
    DataFlowNode n=bList.get(index);
    if (n.isType(NodeType.DO_EXPR) || n.isType(NodeType.FOR_INIT) || n.isType(NodeType.WHILE_EXPR) || n.isType(NodeType.SWITCH_START)) {
      findEnds++;
    }
    if (n.isType(NodeType.WHILE_LAST_STATEMENT) || n.isType(NodeType.SWITCH_END) || n.isType(NodeType.FOR_END) || n.isType(NodeType.DO_EXPR)) {
      if (findEnds > 1) {
        findEnds--;
      }
 else {
        break;
      }
    }
    if (n.isType(NodeType.LABEL_LAST_STATEMENT)) {
      Node parentNode=n.getNode().getFirstParentOfType(dataFlowHandler.getLabelStatementNodeClass());
      if (parentNode == null) {
        break;
      }
 else {
        String label=node.getNode().getImage();
        if (label == null || label.equals(parentNode.getImage())) {
          node.removePathToChild(node.getChildren().get(0));
          DataFlowNode last=bList.get(index + 1);
          node.addPathToChild(last);
          break;
        }
      }
    }
  }
  LOGGER.exiting(CLASS_NAME,"getNodeToBreakSttement");
  return node.getFlow().get(index + 1);
}
