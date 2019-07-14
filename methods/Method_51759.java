@Override public VisitorStarter getDataFlowFacade(){
  return new VisitorStarter(){
    @Override public void start(    Node rootNode){
      new DataFlowFacade().initializeWith(getDataFlowHandler(),(ASTCompilationUnit)rootNode);
    }
  }
;
}
