public boolean isRemovedRelation(Long relationId){
  return deletedRelations.containsKey(relationId);
}
