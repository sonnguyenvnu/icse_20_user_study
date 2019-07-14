private void computeDo(int first,int last){
  LOGGER.entering(CLASS_NAME,"computeDo");
  DataFlowNode doSt=this.braceStack.get(first).getDataFlowNode();
  DataFlowNode doExpr=this.braceStack.get(last).getDataFlowNode();
  DataFlowNode doFirst=doSt.getFlow().get(doSt.getIndex() + 1);
  if (doFirst.getIndex() != doExpr.getIndex()) {
    doExpr.addPathToChild(doFirst);
  }
  LOGGER.exiting(CLASS_NAME,"computeDo");
}
