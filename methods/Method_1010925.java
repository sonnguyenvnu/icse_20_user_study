public void build(final DataFlowBuilderContext _context){
  _context.getBuilder().build((SNode)SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10f3ee082d8L,0x10f3ee0cd6fL,"throwable")));
  SNode interrupt=SNodeOperations.getNodeAncestor(_context.getNode(),MetaAdapterFactory.getInterfaceConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x7c8556154508e980L,"jetbrains.mps.baseLanguage.structure.IControlFlowInterrupter"),false,false);
  SNode tryCatch=SNodeOperations.getNodeAncestor(_context.getNode(),MetaAdapterFactory.getInterfaceConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x3399756d2c03d422L,"jetbrains.mps.baseLanguage.structure.ITryCatchStatement"),false,false);
  if (tryCatch != null && (interrupt == null || ListSequence.fromList(SNodeOperations.getNodeAncestors(tryCatch,null,false)).contains(interrupt))) {
    for (    SNode catchClause : ITryCatchStatement__BehaviorDescriptor.getCatchClauses_id3eptmOG0XgA.invoke(tryCatch)) {
      SNode caughtType=SLinkOperations.getTarget(SLinkOperations.getTarget(catchClause,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10f39a56e2fL,0x10f39a6a2f1L,"throwable")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x450368d90ce15bc3L,0x4ed4d318133c80ceL,"type"));
      if (TypecheckingFacade.getFromContext().isSubtype(TypecheckingFacade.getFromContext().getTypeOf(SLinkOperations.getTarget(_context.getNode(),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10f3ee082d8L,0x10f3ee0cd6fL,"throwable"))),caughtType)) {
        _context.getBuilder().emitJump(_context.getBuilder().before(catchClause),"r:00000000-0000-4000-0000-011c895902c2(jetbrains.mps.baseLanguage.dataFlow)/8754905177066818389");
        return;
      }
    }
  }
  _context.getBuilder().emitRet("r:00000000-0000-4000-0000-011c895902c2(jetbrains.mps.baseLanguage.dataFlow)/1206464652781");
}
