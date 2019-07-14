public static boolean isSystemRelationTypeId(long id){
  return VertexIDType.SystemEdgeLabel.is(id) || VertexIDType.SystemPropertyKey.is(id);
}
