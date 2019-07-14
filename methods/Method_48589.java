@Override public RelationTypeIndex buildEdgeIndex(EdgeLabel label,String name,Direction direction,PropertyKey... sortKeys){
  return buildRelationTypeIndex(label,name,direction,Order.ASC,sortKeys);
}
