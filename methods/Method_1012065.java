private void populate(){
  final VisibleModuleRegistry visibleModules=VisibleModuleRegistry.getInstance();
  final ConditionalScope scope=new ConditionalScope(new GlobalScope(myProject.getRepository()),visibleModules::isVisible,null);
  List<SModule> modules=IterableUtil.asList(scope.getModules());
{
    ModulePoolNamespaceBuilder builder=new ModulePoolNamespaceBuilder();
    TextTreeNode solutions=new TextTreeNode("Solutions");
    for (    SModule s : modules) {
      if (s instanceof Solution || s instanceof ProjectStructureModule) {
        builder.addNode(ProjectModuleTreeNode.createFor(myProject,s,true));
      }
    }
    builder.fillNode(solutions);
    add(solutions);
  }
{
    ModulePoolNamespaceBuilder builder=new ModulePoolNamespaceBuilder();
    TextTreeNode languages=new TextTreeNode("Languages");
    for (    SModule m : modules) {
      if (m instanceof Language) {
        builder.addNode(ProjectModuleTreeNode.createFor(myProject,m,true));
      }
    }
    builder.fillNode(languages);
    add(languages);
  }
{
    ModulePoolNamespaceBuilder builder=new ModulePoolNamespaceBuilder();
    TextTreeNode devkits=new TextTreeNode("DevKits");
    for (    SModule m : modules) {
      if (m instanceof DevKit) {
        builder.addNode(ProjectModuleTreeNode.createFor(myProject,m,true));
      }
    }
    builder.fillNode(devkits);
    add(devkits);
  }
}
