public void execute(SNode node){
  SNode expr=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506fL,"jetbrains.mps.baseLanguage.structure.Expression"));
  Object value=Expression__BehaviorDescriptor.getCompileTimeConstantValue_idi1LP2xI.invoke(expr,SNodeOperations.getModel(expr).getModule());
  if (value instanceof Boolean) {
    SNode v=SNodeFactoryOperations.replaceWithNewChild(expr,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b201L,"jetbrains.mps.baseLanguage.structure.BooleanConstant"));
    SPropertyOperations.assign(v,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b201L,0xf8cc56b202L,"value"),((Boolean)value).booleanValue());
  }
}
