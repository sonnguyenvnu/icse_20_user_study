public void execute(SNode node){
  if (SNodeOperations.isInstanceOf(((SNode)ChangeMethodReturnType_QuickFix.this.getField("expression")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration"))) {
    SLinkOperations.setTarget(SNodeOperations.cast(((SNode)ChangeMethodReturnType_QuickFix.this.getField("expression")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1fdL,"returnType"),TypecheckingFacade.getFromContext().getTypeOf(((SNode)ChangeMethodReturnType_QuickFix.this.getField("desiredType")[0])));
  }
}
