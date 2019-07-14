public List<EntryList> edgeMultiQuery(LongArrayList vertexIdsAsLongs,SliceQuery query,BackendTransaction tx){
  Preconditions.checkArgument(vertexIdsAsLongs != null && !vertexIdsAsLongs.isEmpty());
  final List<StaticBuffer> vertexIds=new ArrayList<>(vertexIdsAsLongs.size());
  for (int i=0; i < vertexIdsAsLongs.size(); i++) {
    Preconditions.checkArgument(vertexIdsAsLongs.get(i) > 0);
    vertexIds.add(idManager.getKey(vertexIdsAsLongs.get(i)));
  }
  final Map<StaticBuffer,EntryList> result=tx.edgeStoreMultiQuery(vertexIds,query);
  final List<EntryList> resultList=new ArrayList<>(result.size());
  for (  StaticBuffer v : vertexIds)   resultList.add(result.get(v));
  return resultList;
}
