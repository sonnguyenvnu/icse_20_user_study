@Override public VisitorStarter getSymbolFacade(){
  return new VisitorStarter(){
    @Override public void start(    Node rootNode){
      new SymbolFacade().initializeWith(null,(ASTCompilationUnit)rootNode);
    }
  }
;
}
