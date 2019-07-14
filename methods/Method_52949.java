@Deprecated @Override public VisitorStarter getDumpFacade(final Writer writer,final String prefix,final boolean recurse){
  return new VisitorStarter(){
    @Override public void start(    Node rootNode){
      new DumpFacade().initializeWith(writer,prefix,recurse,(XmlNode)rootNode);
    }
  }
;
}
