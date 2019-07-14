private void computeSwitch(int firstIndex,int lastIndex){
  LOGGER.entering(CLASS_NAME,"computeSwitch");
  int diff=lastIndex - firstIndex;
  boolean defaultStatement=false;
  DataFlowNode sStart=this.braceStack.get(firstIndex).getDataFlowNode();
  DataFlowNode sEnd=this.braceStack.get(lastIndex).getDataFlowNode();
  DataFlowNode end=sEnd.getChildren().get(0);
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("Stack(sStart)=>" + sStart + ",Stack(sEnd)=>" + sEnd + ",end=>" + end);
  }
  for (int i=0; i < diff - 2; i++) {
    StackObject so=this.braceStack.get(firstIndex + 2 + i);
    DataFlowNode node=so.getDataFlowNode();
    if (LOGGER.isLoggable(Level.FINE)) {
      LOGGER.fine("so(" + (firstIndex + 2 + i) + ")=>" + so + " has  dfn=>" + node + " with first child =>" + node.getChildren().get(0));
    }
    sStart.addPathToChild(node.getChildren().get(0));
    if (so.getType() == NodeType.SWITCH_LAST_DEFAULT_STATEMENT) {
      defaultStatement=true;
    }
  }
  if (!defaultStatement) {
    sStart.addPathToChild(end);
  }
  LOGGER.exiting(CLASS_NAME,"computeSwitch");
}
