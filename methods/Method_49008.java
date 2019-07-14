@Override public Iterable<PropertyKey> getPropertyKeysDirect(){
  RelationCache map=getPropertyMap();
  List<PropertyKey> types=new ArrayList<>(map.numProperties());
  for (  LongObjectCursor<Object> entry : map) {
    types.add(tx().getExistingPropertyKey(entry.key));
  }
  return types;
}
