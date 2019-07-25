public void build(final DataFlowBuilderContext _context){
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x335c206b02bc2de5L,0x335c206b02bde745L,"target")));
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x335c206b02bc2de5L,0x65dd44b0af81a086L,"project")));
  for (  SNode parameter : ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x3ecd7c84cde345deL,0x886c135ecc69b742L,0x335c206b02bc2de5L,0x335c206b02bed2aaL,"parameters")))) {
    _context.getBuilder().build((SNode)parameter);
  }
}
