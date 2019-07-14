private void computeIf(int first,int second,int last){
  LOGGER.entering(CLASS_NAME,"computeIf(3)",first + "," + second + "," + last);
  DataFlowNode ifStart=this.braceStack.get(first).getDataFlowNode();
  DataFlowNode ifEnd=this.braceStack.get(second).getDataFlowNode();
  DataFlowNode elseEnd=this.braceStack.get(last).getDataFlowNode();
  DataFlowNode elseStart=ifEnd.getFlow().get(ifEnd.getIndex() + 1);
  DataFlowNode end=elseEnd.getFlow().get(elseEnd.getIndex() + 1);
  LOGGER.log(Level.FINEST,"If ifstart={0}, ifEnd={1}, elseEnd={2}, elseStart={3}, end={4}",new Object[]{ifStart,ifEnd,elseEnd,elseStart,end});
  if (ifStart.getIndex() != ifEnd.getIndex() && ifEnd.getIndex() != elseEnd.getIndex()) {
    elseStart.reverseParentPathsTo(end);
    ifStart.addPathToChild(elseStart);
  }
 else   if (ifStart.getIndex() == ifEnd.getIndex() && ifEnd.getIndex() != elseEnd.getIndex()) {
    ifStart.addPathToChild(end);
  }
 else   if (ifEnd.getIndex() == elseEnd.getIndex() && ifStart.getIndex() != ifEnd.getIndex()) {
    ifStart.addPathToChild(end);
  }
  LOGGER.exiting(CLASS_NAME,"computeIf(3)");
}
