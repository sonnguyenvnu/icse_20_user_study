public void execute(SNode node){
  SNode instanceOfExpression=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfbbff03700L,"jetbrains.mps.baseLanguage.structure.InstanceOfExpression"));
  SNodeOperations.replaceWithAnother(instanceOfExpression,SLinkOperations.getTarget(instanceOfExpression,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfbbff03700L,0xfbbff06218L,"leftExpression")));
  SNodeOperations.replaceWithAnother(((SNode)FixInstanceOfExpressionPrecedences_QuickFix.this.getField("expressionRoot")[0]),instanceOfExpression);
  SLinkOperations.setTarget(instanceOfExpression,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfbbff03700L,0xfbbff06218L,"leftExpression"),((SNode)FixInstanceOfExpressionPrecedences_QuickFix.this.getField("expressionRoot")[0]));
}
