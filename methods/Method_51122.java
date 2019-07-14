private void computeIf(int first,int last){
  LOGGER.entering(CLASS_NAME,"computeIf(2)");
  DataFlowNode ifStart=this.braceStack.get(first).getDataFlowNode();
  DataFlowNode ifEnd=this.braceStack.get(last).getDataFlowNode();
  if (ifStart.getIndex() != ifEnd.getIndex()) {
    DataFlowNode end=ifEnd.getFlow().get(ifEnd.getIndex() + 1);
    ifStart.addPathToChild(end);
  }
  LOGGER.exiting(CLASS_NAME,"computeIf(2)");
}
