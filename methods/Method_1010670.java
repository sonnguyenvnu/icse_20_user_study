private static SNode meet(SNode node1,SNode node2){
  SNode meetType=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"));
  if (SNodeOperations.isInstanceOf(node1,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"))) {
    SNode meetWrapper1=SNodeOperations.cast(node1,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"));
    for (    SNode bc : SLinkOperations.getChildren(meetWrapper1,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))) {
      ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
    }
    if (SNodeOperations.isInstanceOf(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"))) {
      SNode meetWrapper2=SNodeOperations.cast(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"));
      for (      SNode bc : SLinkOperations.getChildren(meetWrapper2,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))) {
        ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
      }
    }
 else {
      ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(node2));
    }
  }
 else   if (SNodeOperations.isInstanceOf(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"))) {
    SNode meetWrapper2=SNodeOperations.cast(node2,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"));
    ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(node1));
    for (    SNode bc : SLinkOperations.getChildren(meetWrapper2,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))) {
      ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(bc));
    }
  }
 else {
    ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(node1));
    ListSequence.fromList(SLinkOperations.getChildren(meetType,MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))).addElement(HUtil.copyIfNecessary(node2));
  }
  return meetType;
}
