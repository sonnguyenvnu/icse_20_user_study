public void build(final DataFlowBuilderContext _context){
  for (  SNode regexp : SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0xdaafa647f1f74b0bL,0xb09669cd7c8408c0L,0x1117f550b6dL,0x1117f554ef2L,"regexp"))) {
    _context.getBuilder().build((SNode)regexp);
  }
}
