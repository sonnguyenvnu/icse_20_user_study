public void execute(SNode node){
  if (!(SNodeOperations.isInstanceOf(((SNode)AddCast_QuickFix.this.getField("expression")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506fL,"jetbrains.mps.baseLanguage.structure.Expression")))) {
    return;
  }
  SNode actualType=(SNodeOperations.isInstanceOf(((SNode)AddCast_QuickFix.this.getField("desiredType")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type")) ? ((SNode)AddCast_QuickFix.this.getField("desiredType")[0]) : TypecheckingFacade.getFromContext().getTypeOf(((SNode)AddCast_QuickFix.this.getField("desiredType")[0])));
  SNode cast=SNodeOperations.replaceWithNewChild(((SNode)AddCast_QuickFix.this.getField("expression")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,"jetbrains.mps.baseLanguage.structure.CastExpression"));
  SLinkOperations.setTarget(cast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4cL,"expression"),((SNode)AddCast_QuickFix.this.getField("expression")[0]));
  SLinkOperations.setTarget(cast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4bL,"type"),SNodeOperations.copyNode(actualType));
  boolean needsParensAroundCastExpression=PrecedenceUtil.needsParensInsideCastExpression(cast);
  if (needsParensAroundCastExpression) {
    SNode parens=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,"jetbrains.mps.baseLanguage.structure.ParenthesizedExpression"));
    SLinkOperations.setTarget(parens,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,0xfb4ed32b80L,"expression"),((SNode)AddCast_QuickFix.this.getField("expression")[0]));
    SLinkOperations.setTarget(cast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4cL,"expression"),parens);
  }
}
