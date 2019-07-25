public void execute(SNode node){
  for (  SNode s : ICheckedNamePolicy__BehaviorDescriptor.getDescendantsToCheck_id4cWf37B8oXl.invoke(((SNode)FixNamingPolicy_QuickFix.this.getField("nodeToFix")[0]))) {
    SPropertyOperations.set(s,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf93d565d10L,0xf93d565d11L,"value"),NameUtil.captionPartWithNamingPolicy(SPropertyOperations.getString(s,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf93d565d10L,0xf93d565d11L,"value"))));
  }
  for (  PropertyReference p : ICheckedNamePolicy__BehaviorDescriptor.getPropertiesToCheck_id4cWf37B8oXP.invoke(((SNode)FixNamingPolicy_QuickFix.this.getField("nodeToFix")[0]))) {
    SPropertyOperations.assign(p.getNode(),p.getProperty(),NameUtil.captionWithNamingPolicy(SPropertyOperations.getString(p.getNode(),p.getProperty())));
  }
}
