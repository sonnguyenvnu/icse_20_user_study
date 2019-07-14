@Override public VisitorStarter getSymbolFacade(final ClassLoader classLoader){
  return new VisitorStarter(){
    @Override public void start(    Node rootNode){
      new SymbolFacade().initializeWith(classLoader,(ASTCompilationUnit)rootNode);
    }
  }
;
}
