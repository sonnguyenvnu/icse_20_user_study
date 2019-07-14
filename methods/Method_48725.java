public long getRelationID(long count,long partition){
  Preconditions.checkArgument(count > 0 && count < relationCountBound,"Invalid count for bound: %s",relationCountBound);
  return constructId(count,partition,null);
}
