public RelationCache readRelation(Entry data,boolean parseHeaderOnly,TypeInspector tx){
  RelationCache map=data.getCache();
  if (map == null || !(parseHeaderOnly || map.hasProperties())) {
    map=parseRelation(data,parseHeaderOnly,tx);
    data.setCache(map);
  }
  return map;
}
