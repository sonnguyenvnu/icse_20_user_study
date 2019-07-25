public void execute(SNode node){
  SNode smc=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfbbebabf09L,"jetbrains.mps.baseLanguage.structure.StaticMethodCall"));
  SLinkOperations.setTarget(smc,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfbbebabf09L,0x10a7588b546L,"classConcept"),SNodeOperations.getNodeAncestor(((SNode)MakeStaticCall_QuickFix.this.getField("staticMethod")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,"jetbrains.mps.baseLanguage.structure.ClassConcept"),false,false));
  SLinkOperations.setTarget(smc,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301adL,"baseMethodDeclaration"),((SNode)MakeStaticCall_QuickFix.this.getField("staticMethod")[0]));
  ResolveUnknownUtil.reattachMethodArguments(((SNode)MakeStaticCall_QuickFix.this.getField("replacee")[0]),smc);
  ResolveUnknownUtil.reattachTypeArguments(((SNode)MakeStaticCall_QuickFix.this.getField("replacee")[0]),smc);
  SNodeOperations.replaceWithAnother(((SNode)MakeStaticCall_QuickFix.this.getField("replacee")[0]),smc);
}
