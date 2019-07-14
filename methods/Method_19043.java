static Map<String,Pair<Section,Integer>> acquireChildrenMap(@Nullable Section currentComponent){
  final HashMap<String,Pair<Section,Integer>> childrenMap=new HashMap<>();
  if (currentComponent == null) {
    return childrenMap;
  }
  final List<Section> children=currentComponent.getChildren();
  if (children == null) {
    throw new IllegalStateException("Children of current section " + currentComponent + " is null!");
  }
  for (int i=0, size=children.size(); i < size; i++) {
    final Section child=children.get(i);
    childrenMap.put(child.getGlobalKey(),new Pair<Section,Integer>(child,i));
  }
  return childrenMap;
}
