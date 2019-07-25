public void execute(SNode node,EditorContext editorContext){
  boolean oldIsErrorIntention=SPropertyOperations.getBoolean(node,MetaAdapterFactory.getProperty(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cc679L,"isErrorIntention"));
  boolean oldIsAvailableInChildNodes=SPropertyOperations.getBoolean(node,MetaAdapterFactory.getProperty(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cc67aL,"isAvailableInChildNodes"));
  SNode oldExecute=SLinkOperations.getTarget(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3e6813L,"executeFunction")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x108bbca0f48L,0x108bbd29b4aL,"body"));
  SNode oldDescription=SLinkOperations.getTarget(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cd0a9L,"descriptionFunction")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x108bbca0f48L,0x108bbd29b4aL,"body"));
  SNode oldIsApplicable=SLinkOperations.getTarget(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cd0abL,"isApplicableFunction")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x108bbca0f48L,0x108bbd29b4aL,"body"));
  SNode oldChildFilter=SLinkOperations.getTarget(SNodeOperations.cast(SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cd0aaL,"childFilterFunction")),MetaAdapterFactory.getConcept(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x323731f511d1c1bbL,"jetbrains.mps.lang.intentions.structure.ChildFilterFunction")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x108bbca0f48L,0x108bbd29b4aL,"body"));
  SNode newIntention=SNodeFactoryOperations.createNewNode(MetaAdapterFactory.getConcept(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x71ffad1474b12a0bL,"jetbrains.mps.lang.intentions.structure.Intention"),null);
  SNode newExecute=SNodeOperations.cast(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.find_id2gzehMfi1$l.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7538218632063981347"),newIntention)).first(),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x340eb2bd2e03d154L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.MethodInstance"));
  SNode newDescription=SNodeOperations.cast(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.find_id2gzehMfi1$l.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7538218632063982514"),newIntention)).first(),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x340eb2bd2e03d154L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.MethodInstance"));
  if (oldIsAvailableInChildNodes) {
    SNodeOperations.replaceWithAnother(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.findPlaceholders_id5ZzANK5B6wZ.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7271443492837194032"),newIntention)).first(),DSLClassMember__BehaviorDescriptor.create_id7ay_HjIOVVe.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7271443492837194032"),SNodeOperations.getModel(node)));
    SNode newIsApplicableInChild=SNodeOperations.cast(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.find_id2gzehMfi1$l.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7271443492837194032"),newIntention)).first(),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x340eb2bd2e03d154L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.MethodInstance"));
    if ((oldChildFilter == null)) {
      ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(newIsApplicableInChild,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(_quotation_createNode_lf8yx8_a0a0a2a51a8());
    }
 else {
      copyFunctionBody(oldChildFilter,newIsApplicableInChild);
      resolveChildFilterRefernce(newIsApplicableInChild,newIsApplicableInChild);
    }
  }
  if ((SLinkOperations.getTarget(node,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3cd0abL,"isApplicableFunction")) != null)) {
    SNodeOperations.replaceWithAnother(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.findPlaceholders_id5ZzANK5B6wZ.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7538218632063982606"),newIntention)).first(),DSLClassMember__BehaviorDescriptor.create_id7ay_HjIOVVe.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7538218632063982606"),SNodeOperations.getModel(node)));
    SNode newIsApplicable=SNodeOperations.cast(Sequence.fromIterable(DSLClassMember__BehaviorDescriptor.find_id2gzehMfi1$l.invoke(SNodeOperations.getNode("r:d3905048-7598-4a84-931a-cbbcbcda146d(jetbrains.mps.lang.intentions.methods)","7538218632063982606"),newIntention)).first(),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x340eb2bd2e03d154L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.MethodInstance"));
    copyFunctionBody(oldIsApplicable,newIsApplicable);
    resolveReference(newIsApplicable,newIsApplicable);
  }
  SPropertyOperations.assign(newIntention,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"),SPropertyOperations.getString(node,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")));
  SLinkOperations.setTarget(newIntention,MetaAdapterFactory.getReferenceLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x71ffad1474b12a0bL,0x10d005a50b96761L,"forConcept"),SLinkOperations.getTarget(node,MetaAdapterFactory.getReferenceLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x2303633a9c3cc675L,0x2303633a9c3e6812L,"forConcept")));
  copyFunctionBody(oldDescription,newDescription);
  resolveReference(newDescription,newDescription);
  copyFunctionBody(oldExecute,newExecute);
  resolveReference(newExecute,newExecute);
  if (oldIsErrorIntention) {
    SLinkOperations.setTarget(newIntention,MetaAdapterFactory.getContainmentLink(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x71ffad1474b12a0bL,0x59427edd75744671L,"priority"),SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xd7a92d38f7db40d0L,0x8431763b0c3c9f20L,0x59427edd75744615L,"jetbrains.mps.lang.intentions.structure.ErrorIntentionPriority")));
  }
  SPropertyOperations.set(newIntention,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x115eca8579fL,"virtualPackage"),SPropertyOperations.getString(node,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x115eca8579fL,"virtualPackage")));
  SModelOperations.addRootNode(SNodeOperations.getModel(node),newIntention);
}
