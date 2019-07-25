@Override public String fun(final SNodeReference ptr){
  String name=new ModelComputeRunnable<String>(new Computable<String>(){
    @Override public String compute(){
      SNode node=SPointerOperations.resolveNode(ptr,myRepo);
      if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d4348057eL,"jetbrains.mps.lang.behavior.structure.ConceptMethodDeclaration"))) {
        SNode containingRoot=SNodeOperations.getContainingRoot(node);
        if (SNodeOperations.isInstanceOf(containingRoot,MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,"jetbrains.mps.lang.behavior.structure.ConceptBehavior"))) {
          SNode behavior=SNodeOperations.cast(containingRoot,MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,"jetbrains.mps.lang.behavior.structure.ConceptBehavior"));
          if (behavior != null) {
            return SPropertyOperations.getString(SLinkOperations.getTarget(behavior,MetaAdapterFactory.getReferenceLink(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,0x11d43447b1fL,"concept")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
          }
        }
 else {
          return ((String)BHReflection.invoke0(containingRoot,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,"jetbrains.mps.lang.core.structure.BaseConcept"),SMethodTrimmedId.create("getPresentation",null,"hEwIMiw")));
        }
        return ((String)BHReflection.invoke0(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x6c6b6a1e379f9408L,"jetbrains.mps.baseLanguage.structure.MethodDeclaration")),MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept"),SMethodTrimmedId.create("getFqName",null,"hEwIO9y")));
      }
 else       if (node != null) {
        return ((SNode)node).getName();
      }
      return "";
    }
  }
).runRead(myRepo.getModelAccess());
  return name;
}
