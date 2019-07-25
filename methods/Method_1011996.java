@Override public void render(@NotNull NavigationTarget element,@NotNull ElementDescriptor presentation){
  presentation.name=element.getPresentation();
  presentation.icon=GlobalIconManager.getInstance().getIconFor(element.getConcept());
  SModelReference modelRef=element.getNodeReference().getModelReference();
  SModuleReference moduleRef=modelRef.getModuleReference();
  presentation.location=moduleRef == null ? String.format("(%s)",modelRef.getModelName()) : String.format("(%s/%s)",moduleRef.getModuleName(),modelRef.getModelName());
}
