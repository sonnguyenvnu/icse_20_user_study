private void fillAccumulationMap(Map<RedisConstant,Map<String,Object>> infoMap,Table<RedisConstant,String,Long> table){
  if (table == null || table.isEmpty()) {
    return;
  }
  Map<String,Object> accMap=infoMap.get(RedisConstant.DIFF);
  if (accMap == null) {
    accMap=new LinkedHashMap<String,Object>();
    infoMap.put(RedisConstant.DIFF,accMap);
  }
  for (  RedisConstant constant : table.rowKeySet()) {
    Map<String,Long> rowMap=table.row(constant);
    accMap.putAll(rowMap);
  }
}
