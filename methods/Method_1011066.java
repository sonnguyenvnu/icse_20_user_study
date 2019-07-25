public void execute(SNode node){
  SNode parens=SNodeFactoryOperations.replaceWithNewChild(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,"jetbrains.mps.baseLanguage.structure.ParenthesizedExpression"));
  SLinkOperations.setTarget(parens,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,0xfb4ed32b80L,"expression"),node);
}
