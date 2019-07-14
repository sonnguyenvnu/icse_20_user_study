public static long stripEntireRelationTypePadding(long id){
  Preconditions.checkArgument(isProperRelationType(id));
  return VertexIDType.UserEdgeLabel.removePadding(id);
}
