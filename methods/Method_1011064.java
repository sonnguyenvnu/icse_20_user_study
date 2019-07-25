public void execute(SNode node){
  SNode constant=SNodeFactoryOperations.replaceWithNewChild(((SNode)SimplifyWholeBinaryLogicalExpressionWithBooleanConstant_QuickFix.this.getField("operation")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b201L,"jetbrains.mps.baseLanguage.structure.BooleanConstant"));
  SPropertyOperations.assign(constant,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b201L,0xf8cc56b202L,"value"),((Boolean)SimplifyWholeBinaryLogicalExpressionWithBooleanConstant_QuickFix.this.getField("value")[0]));
}
