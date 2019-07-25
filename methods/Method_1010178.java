@Nullable @Override public final SReference create(@NotNull PostponedReference ref){
  try {
    Object result=expandReferenceMacro(ref);
    if (result instanceof SNode) {
      final SNode outputTargetNode=checkOutputNode(ref,(SNode)result);
      return createStaticReference(ref,outputTargetNode);
    }
 else     if (result instanceof String) {
      final String resolveInfoForDynamicResolve=(String)result;
      final SReference dr=createDynamicReference(ref,resolveInfoForDynamicResolve,new DynamicReferenceOrigin(getMacroNodeRef(),null));
      ref.getGenerator().registerDynamicReference(dr);
      return dr;
    }
 else     if (result instanceof SNodeReference) {
      SNodeReference refTarget=(SNodeReference)result;
      return jetbrains.mps.smodel.SReference.create(ref.getLink(),ref.getSourceNode(),refTarget.getModelReference(),refTarget.getNodeId());
    }
    if (!ref.getLink().isOptional()) {
      return createInvalidReference(ref,getInvalidReferenceResolveInfo());
    }
    return null;
  }
 catch (  GenerationFailureException ex) {
    ref.getGenerator().getLogger().handleException(ex);
    ref.getGenerator().getLogger().error(getMacroNodeRef(),ex.getMessage(),GeneratorUtil.describe(ref.getSourceNode(),"source node"));
    return createInvalidReference(ref,getInvalidReferenceResolveInfo());
  }
}
