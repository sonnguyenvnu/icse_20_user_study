@Override public boolean accept(final Key key,final Value value){
  final String group=elementConverter.getGroupFromColumnFamily(key.getColumnFamilyData().getBackingArray());
  if (groupsWithoutFilters.contains(group)) {
    return true;
  }
  final Element element;
  if (schema.isEntity(group)) {
    element=new LazyEntity(new Entity(group),new AccumuloEntityValueLoader(group,key,value,elementConverter,schema));
  }
 else {
    element=new LazyEdge(new Edge(group,null,null,false),new AccumuloEdgeValueLoader(group,key,value,elementConverter,schema,true));
  }
  return elementPredicate.test(element);
}
