public static SNode unbound(SNode maybeBound){
  SNode res=null;
  List<SNode> q=ListSequence.fromListAndArray(new LinkedList<SNode>(),SNodeOperations.copyNode(maybeBound));
  while (!(ListSequence.fromList(q).isEmpty())) {
    SNode n=ListSequence.fromList(q).removeElementAt(0);
    if (SNodeOperations.isInstanceOf(n,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110daeaa84aL,"jetbrains.mps.baseLanguage.structure.UpperBoundType"))) {
      n=SNodeOperations.replaceWithAnother(n,SLinkOperations.getTarget(SNodeOperations.cast(n,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110daeaa84aL,"jetbrains.mps.baseLanguage.structure.UpperBoundType")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110daeaa84aL,0x110daeaa84bL,"bound")));
    }
    if (SNodeOperations.isInstanceOf(n,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110dae9d53dL,"jetbrains.mps.baseLanguage.structure.LowerBoundType"))) {
      n=SNodeOperations.replaceWithAnother(n,SLinkOperations.getTarget(SNodeOperations.cast(n,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110dae9d53dL,"jetbrains.mps.baseLanguage.structure.LowerBoundType")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x110dae9d53dL,0x110dae9f25bL,"bound")));
    }
    if ((n != null)) {
      ListSequence.fromList(q).addSequence(ListSequence.fromList(SNodeOperations.getChildren(n)));
    }
    if ((res == null)) {
      res=n;
    }
    break;
  }
  return res;
}
