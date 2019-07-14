private static IndexUpdate.Type getUpdateType(InternalRelation relation){
  assert relation.isNew() || relation.isRemoved();
  return (relation.isNew() ? IndexUpdate.Type.ADD : IndexUpdate.Type.DELETE);
}
