private static boolean isProperRelationType(long id){
  return VertexIDType.UserEdgeLabel.is(id) || VertexIDType.SystemEdgeLabel.is(id) || VertexIDType.UserPropertyKey.is(id) || VertexIDType.SystemPropertyKey.is(id);
}
