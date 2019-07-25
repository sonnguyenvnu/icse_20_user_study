@Override public void resolve(@NotNull ReferenceResolver resolver){
  ReferenceInfo_Macro refInfo=new ReferenceInfo_Macro(resolver);
  new PostponedReference(resolver.getReferenceRole(),resolver.getOutputNode(),refInfo).registerWith(generator);
}
