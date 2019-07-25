@Override public boolean populate(MPSTreeNode treeNode,Generator generator){
  if (myProjectPane.isDescriptorModelInGeneratorVisible()) {
    return false;
  }
  Predicate<SModel> isDescriptorModel=SModelStereotype::isDescriptorModel;
  new SModelsSubtree(treeNode).create(generator.getModels().stream().filter(isDescriptorModel.negate()).collect(Collectors.toList()));
  return true;
}
