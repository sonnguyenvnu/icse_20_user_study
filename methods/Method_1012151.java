@Override public boolean populate(MPSTreeNode treeNode,Solution solution){
  if (myProjectPane.isDescriptorModelInSolutionVisible()) {
    @SuppressWarnings("SimplifyOptionalCallChains") final SModel descriptorModel=solution.getModels().stream().filter(SModelStereotype::isDescriptorModel).findFirst().orElse(null);
    if (descriptorModel != null) {
      treeNode.add(new SModelTreeNode(descriptorModel,new LongModelNameText()));
    }
  }
  return false;
}
