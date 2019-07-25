private MainNodeData build(final SearchResults<?> results,final INodeRepresentator nodeRepresentator){
  myRebuildCache=new HashMap<>();
  MainNodeData root=new MainNodeData(PathItemRole.ROLE_MAIN_ROOT);
  SearchedNodesNodeData nodesRoot=new SearchedNodesNodeData(PathItemRole.ROLE_MAIN_SEARCHED_NODES);
  root.addChild(nodesRoot);
  final PathProvider pp1=new PathProvider(null,false);
  for (  Object node : results.getSearchedObjects().getElements()) {
    if (node != null) {
      createPath(pp1,nodesRoot,new SearchResult<>(node,SearchedNodesNodeData.CATEGORY_NAME));
    }
  }
  final List<? extends SearchResult<?>> notNullResults=results.getNotNullResults();
  final Icon i;
  final String c;
  if (nodeRepresentator == null) {
    i=null;
    c=NameUtil.formatNumericalString(notNullResults.size(),"usage") + " found";
  }
 else {
    i=nodeRepresentator.getResultsIcon();
    c=nodeRepresentator.getResultsText(new TextOptions(true,false,notNullResults.size()));
  }
  ResultsNodeData resultsRoot=new ResultsNodeData(PathItemRole.ROLE_MAIN_RESULTS,i,c);
  root.addChild(resultsRoot);
  final PathProvider pp2=new PathProvider(nodeRepresentator,true);
  for (  SearchResult<?> result : notNullResults) {
    createPath(pp2,resultsRoot,result);
  }
  myRebuildCache=null;
  return root;
}
