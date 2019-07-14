private Long getCommonCount(Map<?,?> infoMap,RedisConstant redisConstant,String commond){
  Object constantObject=infoMap.get(redisConstant) == null ? infoMap.get(redisConstant.getValue()) : infoMap.get(redisConstant);
  if (constantObject != null && (constantObject instanceof Map)) {
    Map constantMap=(Map)constantObject;
    if (constantMap == null || constantMap.get(commond) == null) {
      return null;
    }
    return MapUtils.getLongValue(constantMap,commond);
  }
  return null;
}
