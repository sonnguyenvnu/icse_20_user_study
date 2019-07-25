public void build(final DataFlowBuilderContext _context){
  for (  SNode p : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0xfd3920347849419dL,0x907112563d152375L,0x117545d385aL,0x117545e58d8L,"parameter")))) {
    _context.getBuilder().build((SNode)p);
  }
}
