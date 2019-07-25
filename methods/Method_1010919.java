public void build(final DataFlowBuilderContext _context){
  if ((ContinueStatement__BehaviorDescriptor.getLoop_idhEwIA0E.invoke(_context.getNode()) != null)) {
    _context.getBuilder().emitJump(_context.getBuilder().before(ContinueStatement__BehaviorDescriptor.getLoop_idhEwIA0E.invoke(_context.getNode())),"r:00000000-0000-4000-0000-011c895902c2(jetbrains.mps.baseLanguage.dataFlow)/1206465288371");
  }
 else {
    _context.getBuilder().emitNop("r:00000000-0000-4000-0000-011c895902c2(jetbrains.mps.baseLanguage.dataFlow)/1409563270992122147");
  }
}
