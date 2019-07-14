private RelationCache getPropertyMap(){
  RelationCache map=data.getCache();
  if (map == null || !map.hasProperties()) {
    map=RelationConstructor.readRelationCache(data,tx());
  }
  return map;
}
