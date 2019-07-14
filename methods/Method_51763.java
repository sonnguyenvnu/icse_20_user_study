@Override public VisitorStarter getQualifiedNameResolutionFacade(final ClassLoader classLoader){
  return new VisitorStarter(){
    @Override public void start(    Node rootNode){
      new QualifiedNameResolver().initializeWith(classLoader,(ASTCompilationUnit)rootNode);
    }
  }
;
}
