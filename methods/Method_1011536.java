@Override protected MPSTreeNode rebuild(){
  List<IWatchable> watchables=myUiState.getWatchables();
  if (watchables.isEmpty()) {
    return createEmptyTree();
  }
  MPSTreeNode rootTreeNode=new TextTreeNode("Local Variables");
  rootTreeNode.setTree(this);
  Map<WatchablesCategory,List<IWatchable>> orphanesByCategory=MapSequence.fromMap(new HashMap<WatchablesCategory,List<IWatchable>>());
  Map<WatchablesCategory,Map<SNodeReference,List<IWatchable>>> nodeToVarsMapByCategory=MapSequence.fromMap(new HashMap<WatchablesCategory,Map<SNodeReference,List<IWatchable>>>());
  for (  IWatchable watchable : watchables) {
    WatchablesCategory category=watchable.getCategory();
    SNodeReference node=(watchable instanceof Watchable2 ? ((Watchable2)watchable).getSourceNode() : ((watchable.getNode() == null ? null : watchable.getNode().getReference())));
    if (node == null) {
      List<IWatchable> orphanes=MapSequence.fromMap(orphanesByCategory).get(category);
      if (orphanes == null) {
        orphanes=new ArrayList<IWatchable>();
        MapSequence.fromMap(orphanesByCategory).put(category,orphanes);
      }
      orphanes.add(watchable);
    }
 else {
      Map<SNodeReference,List<IWatchable>> nodeToVarsMap=MapSequence.fromMap(nodeToVarsMapByCategory).get(category);
      if (nodeToVarsMap == null) {
        nodeToVarsMap=MapSequence.fromMap(new LinkedHashMap<SNodeReference,List<IWatchable>>(16,(float)0.75,false));
        MapSequence.fromMap(nodeToVarsMapByCategory).put(category,nodeToVarsMap);
      }
      List<IWatchable> watchableList=MapSequence.fromMap(nodeToVarsMap).get(node);
      if (watchableList == null) {
        watchableList=ListSequence.fromList(new ArrayList<IWatchable>());
        MapSequence.fromMap(nodeToVarsMap).put(node,watchableList);
      }
      ListSequence.fromList(watchableList).addElement(watchable);
    }
  }
  SortedSet<WatchablesCategory> keys=SortedSetSequence.fromSetWithValues(new TreeSet<WatchablesCategory>(),SetSequence.fromSet(MapSequence.fromMap(orphanesByCategory).keySet()).union(SetSequence.fromSet(MapSequence.fromMap(nodeToVarsMapByCategory).keySet())));
  for (  WatchablesCategory category : keys) {
    List<IWatchable> orphanes=MapSequence.fromMap(orphanesByCategory).get(category);
    Map<SNodeReference,List<IWatchable>> nodeToVarsMap=MapSequence.fromMap(nodeToVarsMapByCategory).get(category);
    if (orphanes == null) {
      orphanes=ListSequence.fromList(new ArrayList<IWatchable>());
    }
    if (nodeToVarsMap == null) {
      nodeToVarsMap=MapSequence.fromMap(new HashMap<SNodeReference,List<IWatchable>>());
    }
    List<SNodeReference> nodes=ListSequence.fromList(new ArrayList<SNodeReference>());
    ListSequence.fromList(nodes).addSequence(SetSequence.fromSet(MapSequence.fromMap(nodeToVarsMap).keySet()));
    Collections.sort(nodes,new ToStringComparator());
    Collections.sort(orphanes,new Comparator<IWatchable>(){
      @Override public int compare(      IWatchable o1,      IWatchable o2){
        return o1.getName().compareTo(o2.getName());
      }
    }
);
    for (    SNodeReference snode : MapSequence.fromMap(nodeToVarsMap).keySet()) {
      List<IWatchable> watchablesWithNodes=MapSequence.fromMap(nodeToVarsMap).get(snode);
      if (ListSequence.fromList(watchablesWithNodes).count() == 1) {
        rootTreeNode.add(new WatchableNode(ListSequence.fromList(watchablesWithNodes).first(),myUiState));
      }
 else {
        NodeTreeNode nodeTreeNode=new NodeTreeNode(snode);
        for (        IWatchable watchable : watchablesWithNodes) {
          nodeTreeNode.add(new WatchableNode(watchable,myUiState));
        }
        rootTreeNode.add(nodeTreeNode);
      }
    }
    for (    IWatchable watchable : orphanes) {
      rootTreeNode.add(new WatchableNode(watchable,myUiState));
    }
  }
  return rootTreeNode;
}
