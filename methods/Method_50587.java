@Override public VisitorStarter getMultifileFacade(){
  return rootNode -> new ApexMultifileVisitorFacade().initializeWith((ApexNode<?>)rootNode);
}
