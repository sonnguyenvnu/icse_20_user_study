private void populate(){
  ShortModelNameText textSource=new ShortModelNameText();
  for (  SModel m : LanguageAspectSupport.getAspectModels(getModule())) {
    add(new SModelTreeNode(m,textSource));
  }
  if (getModule().getAccessoryModels().size() > 0) {
    TextTreeNode accessories=new AccessoriesModelTreeNode(this);
    List<SModel> sortedModels=SortUtil.sortModels(getModule().getAccessoryModels());
    LongModelNameText modelText=new LongModelNameText();
    for (    SModel model : sortedModels) {
      SModule m=model.getModule();
      boolean currentModule=m == null || m == getModule();
      if (!currentModule) {
        accessories.add(new SModelReferenceTreeNode(model,myProject));
      }
 else {
        accessories.add(new SModelTreeNode(model,modelText));
      }
    }
    this.add(accessories);
  }
  for (  Generator generator : getModule().getOwnedGenerators()) {
    MPSTreeNode generatorNode=createFor(myProject,generator);
    add(generatorNode);
  }
  if (!getModule().getRuntimeModulesReferences().isEmpty()) {
    TextTreeNode languageRuntime=new RuntimeModulesTreeNode();
    for (    SModuleReference mr : getModule().getRuntimeModulesReferences()) {
      languageRuntime.add(new SModuleReferenceTreeNode(mr,myProject));
    }
    add(languageRuntime);
  }
  if (getModule().getUtilModels().size() > 0) {
    TextTreeNode utilModels=new SModelGroupTreeNode();
    new SModelsSubtree(utilModels).create(getModule().getUtilModels());
    this.add(utilModels);
  }
  TextTreeNode allModels=new AllModelsTreeNode();
  allModels.setIcon(Nodes.ModuleGroup);
  new SModelsSubtree(allModels,false,false).create(getModule());
  add(allModels);
}
