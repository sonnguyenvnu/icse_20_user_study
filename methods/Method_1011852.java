public void build(final DataFlowBuilderContext _context){
  for (  SNode item : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3304fc6e7c6b401eL,0xa016b944934bb21fL,0x42d5783a626b0a85L,0x42d5783a626b0a8dL,"components")))) {
    _context.getBuilder().build((SNode)item);
  }
}
