@Override public String fun(final SNodeReference ptr){
  String name=new ModelComputeRunnable<String>(new Computable<String>(){
    @Override public String compute(){
      SNode node=SPointerOperations.resolveNode(ptr,myRepo);
      if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"))) {
        return SPropertyOperations.getString(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
      }
 else       if (node != null) {
        return ((String)BHReflection.invoke0(node,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,"jetbrains.mps.lang.core.structure.BaseConcept"),SMethodTrimmedId.create("getPresentation",null,"hEwIMiw")));
      }
      return "";
    }
  }
).runRead(myRepo.getModelAccess());
  return name;
}
