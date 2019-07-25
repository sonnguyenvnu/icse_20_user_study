@Override protected MPSTreeNode rebuild(){
  if (myModule == null) {
    return new TextTreeNode("No Content");
  }
  DepLink deps=new DependencyUtil(myModule.getRepository()).trackRuntime(isShowRuntime()).build(myModule);
  TextTreeNode root=new TextTreeNode(myModule.getModuleName());
  root.setIcon(myProject.getComponent(GlobalIconManager.class).getIconFor(myModule));
  populate(root,deps.allDependencies());
  return root;
}
