public static long stripRelationTypePadding(long id){
  Preconditions.checkArgument(isProperRelationType(id));
  return VertexIDType.RelationType.removePadding(id);
}
