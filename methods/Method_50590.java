@Deprecated @Override public VisitorStarter getDumpFacade(Writer writer,String prefix,boolean recurse){
  return rootNode -> new DumpFacade().initializeWith(writer,prefix,recurse,(ApexNode<?>)rootNode);
}
