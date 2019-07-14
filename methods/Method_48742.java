public static long addRelationTypePadding(long id){
  long typeId=VertexIDType.RelationType.addPadding(id);
  Preconditions.checkArgument(isProperRelationType(typeId));
  return typeId;
}
