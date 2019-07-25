public static RoutingTable build(ClusterService clusterService,ClusterState clusterState,Index index){
  ImmutableOpenMap.Builder<String,IndexRoutingTable> indicesRoutingMap=new ImmutableOpenMap.Builder<>();
  for (  ObjectObjectCursor<String,IndexRoutingTable> entry : clusterState.routingTable().indicesRouting()) {
    if (!entry.key.equals(index))     indicesRoutingMap.put(entry.value.getIndex().getName(),entry.value);
  }
  IndexMetaData indexMetaData=clusterState.metaData().index(index);
  if (indexMetaData != null) {
    IndexRoutingTable.Builder indexRoutingTableBuilder=new IndexRoutingTable.Builder(index,clusterService,clusterState);
    if (indexRoutingTableBuilder.shards.size() > 0)     indicesRoutingMap.put(index.getName(),indexRoutingTableBuilder.build());
  }
  return new RoutingTable(clusterState.routingTable().version(),indicesRoutingMap.build());
}
