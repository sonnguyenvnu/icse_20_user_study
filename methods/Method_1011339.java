public void build(final DataFlowBuilderContext _context){
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x1fe4fcef62d0186cL,0x1fe4fcef62d01870L,"target")));
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x1fe4fcef62d0186cL,0x1fe4fcef62d01872L,"project")));
  for (  SNode parameter : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x1fe4fcef62d0186cL,0x1fe4fcef62d01871L,"parameters")))) {
    _context.getBuilder().build((SNode)parameter);
  }
}
