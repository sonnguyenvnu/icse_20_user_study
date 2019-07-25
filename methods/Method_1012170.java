public StructureViewBuilder create(MPSProject mpsProject,SNodeReference np){
  mpsProject.getModelAccess().checkReadAccess();
  SNode node=np.resolve(mpsProject.getRepository());
  List<RelationDescriptor> tabs=new ArrayList<>();
  for (  RelationDescriptor tab : mpsProject.getProject().getComponent(ProjectPluginManager.class).getTabDescriptors()) {
    try {
      if (tab.getBaseNode(node) != null || tab.isApplicable(node)) {
        tabs.add(tab);
      }
    }
 catch (    Throwable t) {
      LOG.error("Exception in extension: ",t);
    }
  }
  for (  RelationDescriptor tab : tabs) {
    SNode baseNode=null;
    try {
      baseNode=tab.getBaseNode(node);
    }
 catch (    Throwable t) {
      LOG.error("Exception in extension: ",t);
    }
    if (baseNode != null && baseNode.getName() != null) {
      return new NodeStructureViewBuilder(mpsProject,baseNode.getReference());
    }
  }
  for (  RelationDescriptor tab : tabs) {
    List<SNode> nodes=null;
    try {
      nodes=tab.getNodes(node);
    }
 catch (    Throwable t) {
      LOG.error("Exception in extension: ",t);
    }
    if (node != null && !nodes.isEmpty()) {
      return new NodeStructureViewBuilder(mpsProject,new jetbrains.mps.smodel.SNodePointer(node));
    }
  }
  return null;
}
