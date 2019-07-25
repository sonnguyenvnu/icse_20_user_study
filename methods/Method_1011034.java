public void execute(SNode node){
  for (  SNode parameter : SLinkOperations.getChildren(((SNode)FixParametersInAnonymousClass_QuickFix.this.getField("anonymousClass")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x1107e0cb103L,0x1107e104a89L,"parameter"))) {
    ListSequence.fromList(SLinkOperations.getChildren(((SNode)FixParametersInAnonymousClass_QuickFix.this.getField("anonymousClass")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301aeL,"actualArgument"))).addElement(parameter);
  }
}
