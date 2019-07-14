public void buildDataFlowFor(JavaNode node){
  if (!(node instanceof ASTMethodDeclaration) && !(node instanceof ASTConstructorDeclaration)) {
    throw new RuntimeException("Can't build a data flow for anything other than a method or a constructor");
  }
  this.dataFlow=new Structure(dataFlowHandler);
  this.dataFlow.createStartNode(node.getBeginLine());
  this.dataFlow.createNewNode(node);
  node.jjtAccept(this,dataFlow);
  this.dataFlow.createEndNode(node.getEndLine());
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("DataFlow is " + this.dataFlow.dump());
  }
  Linker linker=new Linker(dataFlowHandler,dataFlow.getBraceStack(),dataFlow.getContinueBreakReturnStack());
  try {
    linker.computePaths();
  }
 catch (  SequenceException|LinkerException e) {
    e.printStackTrace();
  }
}
