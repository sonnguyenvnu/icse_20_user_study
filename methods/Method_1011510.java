public void build(final DataFlowBuilderContext _context){
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x1117f3d1d35L,"leftExpression")));
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x1117f3d1d36L,"rightExpression")));
  if ((SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x112d82366d0L,"errorString")) != null)) {
    _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x112d82366d0L,"errorString")));
  }
  if ((SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x1117f58cf99L,"nodeToCheck")) != null)) {
    _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x1117f58cf99L,"nodeToCheck")));
  }
  if (ListSequence.fromList(SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x11b2b6fabb9L,"helginsIntention"))).isNotEmpty()) {
    for (    SNode intetntion : SLinkOperations.getChildren(_context.getNode(),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1117f3c1ffaL,0x11b2b6fabb9L,"helginsIntention"))) {
      _context.getBuilder().build((SNode)intetntion);
    }
  }
}
