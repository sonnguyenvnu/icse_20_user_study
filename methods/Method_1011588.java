public void build(final DataFlowBuilderContext _context){
  for (  SNode arg : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0xf3347d8a0e794f35L,0x8ac91574f25c986fL,0xbe3a0d5ba1a2bf4L,0xbe3a0d5ba1a2bf5L,"argument")))) {
    _context.getBuilder().build((SNode)SLinkOperations.getTarget(arg,MetaAdapterFactory.getContainmentLink(0xf3347d8a0e794f35L,0x8ac91574f25c986fL,0xbe3a0d5ba1a2be4L,0xbe3a0d5ba1a2be6L,"value")));
  }
}
