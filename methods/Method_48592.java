@Override public RelationTypeIndex buildPropertyIndex(PropertyKey key,String name,org.apache.tinkerpop.gremlin.process.traversal.Order sortOrder,PropertyKey... sortKeys){
  return buildRelationTypeIndex(key,name,Direction.OUT,Order.convert(sortOrder),sortKeys);
}
