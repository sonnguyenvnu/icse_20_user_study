protected Map groupByFunction(List<TreeItem<S>> items,TreeTableColumn<S,?> column){
  Map<Object,List<TreeItem<S>>> map=new HashMap<>();
  for (  TreeItem<S> child : items) {
    Object key=column.getCellData(child);
    map.computeIfAbsent(key,k -> new ArrayList<>());
    map.get(key).add(child);
  }
  return map;
}
