private Cursor find(Session session){
  return new SpatialCursor(treeMap.keySet().iterator(),table,session);
}
