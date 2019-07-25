public void build(final DataFlowBuilderContext _context){
  for (  SNode initializer : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x817e4e70961e4a95L,0x98a115e9f32231f1L,0x4027f9073ff5ce93L,0x4027f9073ff652f7L,"initializer")))) {
    _context.getBuilder().build((SNode)initializer);
  }
}
