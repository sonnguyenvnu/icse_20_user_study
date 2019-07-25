@Nullable @Override public SReference create(@NotNull PostponedReference ref){
  SNode outputTargetNode=doResolve_Straightforward(ref);
  if (outputTargetNode != null) {
    return createStaticReference(ref,outputTargetNode);
  }
  if (myResolveInfo != null) {
    final SNodeReference inputNodeRef=myContext.getInput() == null ? null : myContext.getInput().getReference();
    final SReference dr=createDynamicReference(ref,myResolveInfo,new DynamicReferenceOrigin(myTemplateSourceNode,inputNodeRef));
    ref.getGenerator().registerDynamicReference(dr);
    return dr;
  }
  return createInvalidReference(ref,null);
}
