private static SNode unmeet(SNode possiblyMeet){
  SNode tmp=possiblyMeet;
  with_meet:   while (SNodeOperations.isInstanceOf(tmp,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType"))) {
    for (    SNode arg : SLinkOperations.getChildren(SNodeOperations.cast(tmp,MetaAdapterFactory.getConcept(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,"jetbrains.mps.lang.typesystem.structure.MeetType")),MetaAdapterFactory.getContainmentLink(0x7a5dda6291404668L,0xab76d5ed1746f2b2L,0x114b68ad132L,0x114b68b040bL,"argument"))) {
      if (!(SNodeOperations.isInstanceOf(arg,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc6bf96dL,"jetbrains.mps.baseLanguage.structure.VoidType")))) {
        tmp=arg;
        continue with_meet;
      }
    }
    return _quotation_createNode_zgotlq_a1a1a2();
  }
  if (SNodeOperations.isInstanceOf(tmp,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType"))) {
    List<SNode> params=SLinkOperations.getChildren(SNodeOperations.cast(tmp,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x102419671abL,"parameter"));
    for (    SNode p : params) {
      SNode up=unmeet(p);
      if (up != p) {
        SNodeOperations.replaceWithAnother(p,up);
      }
    }
  }
  return tmp;
}
