public void apply(SModel model){
  makeReferencesDirect(model);
  ArrayList<SNode> nodes=new ArrayList<>();
  model.getRootNodes().forEach(nodes::add);
  nodes.forEach(model::removeRootNode);
  if (mySortComparator != null) {
    nodes.sort(mySortComparator);
  }
  nodes.forEach(this::processTree);
  nodes.forEach(model::addRootNode);
}
