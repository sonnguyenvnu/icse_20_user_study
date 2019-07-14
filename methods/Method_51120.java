private void computeWhile(int first,int last){
  LOGGER.entering(CLASS_NAME,"computeWhile with first and last of" + first + "," + last);
  DataFlowNode wStart=this.braceStack.get(first).getDataFlowNode();
  DataFlowNode wEnd=this.braceStack.get(last).getDataFlowNode();
  DataFlowNode end=wEnd.getFlow().get(wEnd.getIndex() + 1);
  if (wStart.getIndex() != wEnd.getIndex()) {
    end.reverseParentPathsTo(wStart);
    wStart.addPathToChild(end);
  }
  LOGGER.exiting(CLASS_NAME,"computeWhile");
}
