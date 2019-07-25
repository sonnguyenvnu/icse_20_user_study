@Override public ActionsTreeBuilder<ActionsModuleBuilder> module(String name){
  Session session=JcrMetadataAccess.getActiveSession();
  try {
    Node securityNode=session.getRootNode().getNode(SecurityPaths.SECURITY.toString());
    this.groupsNode=this.groupsNode == null || !this.groupsNode.getSession().isLive() ? session.getRootNode().getNode(this.protoModulesPath) : this.groupsNode;
    this.protoActionsNode=JcrUtil.getOrCreateNode(groupsNode,name,JcrAllowedActions.NODE_TYPE);
    return new JcrActionTreeBuilder<>(protoActionsNode,this);
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Failed to access root node for allowable actions",e);
  }
}
