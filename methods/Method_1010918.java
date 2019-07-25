public void build(final DataFlowBuilderContext _context){
  for (  SNode var : ListSequence.fromList(Closure__BehaviorDescriptor.getVariablesReferencedInClosure_idhNVujlz.invoke(_context.getNode()))) {
    _context.getBuilder().emitRead(var,"r:00000000-0000-4000-0000-011c895902c2(jetbrains.mps.baseLanguage.dataFlow)/1223990218854");
  }
}
