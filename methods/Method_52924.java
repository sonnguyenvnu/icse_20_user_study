@Deprecated @Override public VisitorStarter getDumpFacade(final Writer writer,final String prefix,final boolean recurse){
  return new VisitorStarter(){
    @Override public void start(    final Node rootNode){
      ((AbstractVmNode)rootNode).dump(prefix,recurse,writer);
    }
  }
;
}
