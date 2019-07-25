public void build(final DataFlowBuilderContext _context){
  for (  SNode textExpression : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x760a0a8ceabb4521L,0x8bfd65db761a9ba3L,0x1100a2cc320L,0x1100a2d9863L,"textExpression")))) {
    _context.getBuilder().build((SNode)textExpression);
  }
}
