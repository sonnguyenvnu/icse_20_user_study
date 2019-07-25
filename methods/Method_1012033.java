@Override protected UsagesTreeNode rebuild(){
  myResultsSubtree=null;
  UsagesTreeNode root=new UsagesTreeNode();
  if (myContents == null || !myContents.getTreeRoot().hasChildren()) {
    return root;
  }
  if (myShowSearchedNodes) {
    HashSet<PathItemRole> searchedNodesPathProvider=new HashSet<>();
    searchedNodesPathProvider.add(PathItemRole.ROLE_MAIN_SEARCHED_NODES);
    BaseNodeData searchedNodesRoot=myContents.getSearchSubtree();
    if (DataTree.getNodeDataStream(searchedNodesRoot).anyMatch(NodeNodeData.class::isInstance)) {
      if (myGroupSearchedNodes) {
        searchedNodesPathProvider.add(PathItemRole.ROLE_ROOT);
        searchedNodesPathProvider.add(PathItemRole.ROLE_ROOT_TO_TARGET_NODE);
      }
      searchedNodesPathProvider.add(PathItemRole.ROLE_TARGET_NODE);
    }
 else     if (DataTree.getNodeDataStream(searchedNodesRoot).anyMatch(ModelNodeData.class::isInstance)) {
      if (myGroupSearchedNodes) {
        searchedNodesPathProvider.add(PathItemRole.ROLE_MODULE);
      }
      searchedNodesPathProvider.add(PathItemRole.ROLE_MODEL);
    }
 else {
      searchedNodesPathProvider.add(PathItemRole.ROLE_MODULE);
    }
    root.add(buildTree(searchedNodesRoot,searchedNodesPathProvider));
  }
  myResultsSubtree=buildTree(myContents.getResultsSubtree(),myResultPathProvider);
  root.add(myResultsSubtree);
  return root;
}
