@Override public RelationTypeIndex buildEdgeIndex(EdgeLabel label,String name,Direction direction,org.apache.tinkerpop.gremlin.process.traversal.Order sortOrder,PropertyKey... sortKeys){
  return buildRelationTypeIndex(label,name,direction,Order.convert(sortOrder),sortKeys);
}
