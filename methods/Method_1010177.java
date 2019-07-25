@Nullable @Override public SReference create(@NotNull PostponedReference ref){
  if (myInputTargetNode != null) {
    SNode ultimateTarget=ref.getGenerator().findCopiedOutputNodeForInputNode_unique(myInputTargetNode);
    if (ultimateTarget != null) {
      return createStaticReference(ref,ultimateTarget);
    }
    String resolveInfo=jetbrains.mps.util.SNodeOperations.getResolveInfo(myInputTargetNode);
    if (resolveInfo != null) {
      final SReference dr=createDynamicReference(ref,resolveInfo,new DynamicReferenceOrigin(null,myInputNode.getReference()));
      ref.getGenerator().registerDynamicReference(dr);
      return dr;
    }
    SNode ambiguousTarget=ref.getGenerator().findCopiedOutputNodeForInputNode(myInputTargetNode);
    if (ambiguousTarget != null) {
      if (checkResolvedTarget(ref,ambiguousTarget)) {
        return createStaticReference(ref,ambiguousTarget);
      }
 else {
        return jetbrains.mps.smodel.SReference.create(ref.getLink(),ref.getSourceNode(),ref.getGenerator().getOutputModel().getReference(),null);
      }
    }
  }
  return createInvalidReference(ref,null);
}
