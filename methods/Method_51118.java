private void computeFor(int firstIndex,int lastIndex){
  LOGGER.entering(CLASS_NAME,"computeFor");
  DataFlowNode fExpr=null;
  DataFlowNode fUpdate=null;
  DataFlowNode fSt=null;
  DataFlowNode fEnd=null;
  boolean isUpdate=false;
  for (int i=firstIndex; i <= lastIndex; i++) {
    StackObject so=this.braceStack.get(i);
    DataFlowNode node=so.getDataFlowNode();
    if (so.getType() == NodeType.FOR_EXPR) {
      fExpr=node;
    }
 else     if (so.getType() == NodeType.FOR_UPDATE) {
      fUpdate=node;
      isUpdate=true;
    }
 else     if (so.getType() == NodeType.FOR_BEFORE_FIRST_STATEMENT) {
      fSt=node;
    }
 else     if (so.getType() == NodeType.FOR_END) {
      fEnd=node;
    }
  }
  DataFlowNode end=fEnd.getFlow().get(fEnd.getIndex() + 1);
  DataFlowNode firstSt=fSt.getChildren().get(0);
  if (isUpdate) {
    if (fSt.getIndex() != fEnd.getIndex()) {
      end.reverseParentPathsTo(fUpdate);
      fExpr.removePathToChild(fUpdate);
      fUpdate.addPathToChild(fExpr);
      fUpdate.removePathToChild(firstSt);
      fExpr.addPathToChild(firstSt);
      fExpr.addPathToChild(end);
    }
 else {
      fSt.removePathToChild(end);
      fExpr.removePathToChild(fUpdate);
      fUpdate.addPathToChild(fExpr);
      fExpr.addPathToChild(fUpdate);
      fExpr.addPathToChild(end);
    }
  }
 else {
    if (fSt.getIndex() != fEnd.getIndex()) {
      end.reverseParentPathsTo(fExpr);
      fExpr.addPathToChild(end);
    }
  }
  LOGGER.exiting(CLASS_NAME,"computeFor");
}
