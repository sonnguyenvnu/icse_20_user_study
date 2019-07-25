private static SNode join(SNode node1,SNode node2){
  SNode joinType=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"));
  if (SNodeOperations.isInstanceOf(node1,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"))) {
    SNode joinWrapper1=SNodeOperations.cast(node1,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"));
    for (    SNode bc : SLinkOperations.getChildren(joinWrapper1,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))) {
      ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
    }
    if (SNodeOperations.isInstanceOf(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"))) {
      SNode joinWrapper2=SNodeOperations.cast(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"));
      for (      SNode bc : SLinkOperations.getChildren(joinWrapper2,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))) {
        ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
      }
    }
 else {
      ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(node2));
    }
  }
 else   if (SNodeOperations.isInstanceOf(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"))) {
    SNode joinWrapper2=SNodeOperations.cast(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,"jetbrains.mps.lang.typesystem.structure.JoinType"));
    ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(node1));
    for (    SNode bc : SLinkOperations.getChildren(joinWrapper2,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))) {
      ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
    }
  }
 else {
    ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(node1));
    ListSequence.fromList(SLinkOperations.getChildren(joinType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x1129e737f02L,0x1129e73a76aL,"argument"))).addElement(HUtil.copyIfNecessary(node2));
  }
  return joinType;
}
