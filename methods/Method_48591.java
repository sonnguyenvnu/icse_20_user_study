@Override public RelationTypeIndex buildPropertyIndex(PropertyKey key,String name,PropertyKey... sortKeys){
  return buildRelationTypeIndex(key,name,Direction.OUT,Order.ASC,sortKeys);
}
