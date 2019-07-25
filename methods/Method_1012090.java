@Override protected MPSTreeNode rebuild(){
  MPSTreeNode result=new TextTreeNode((ListSequence.fromList(myAllDependencies).isEmpty() ? "No Dependencies Selected" : "Found Dependencies:"));
  Map<Dependency,DependencyPathTree.LinkFrom> deps=MapSequence.fromMap(new HashMap<Dependency,DependencyPathTree.LinkFrom>());
  for (  DepLink dep : ListSequence.fromList(myAllDependencies)) {
    buildTree(dep,deps);
  }
  for (  DependencyPathTree.LinkFrom lf : Sequence.fromIterable(MapSequence.fromMap(deps).values())) {
    if (lf.parent == null) {
      result.add(lf.node);
    }
  }
  setRootVisible(result.getChildCount() == 0);
  setShowsRootHandles(result.getChildCount() != 0);
  return result;
}
