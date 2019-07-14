public Map<Long,Map<String,Object>> getMutableVertexProperties(){
  return Maps.transformValues(vertexStates,vs -> {
    Map<String,Object> map=new HashMap<>(elementKeyMap.size());
    for (    String key : elementKeyMap.keySet()) {
      Object v=vs.getProperty(key,elementKeyMap);
      if (v != null)       map.put(key,v);
    }
    return map;
  }
);
}
