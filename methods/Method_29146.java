public String getTypeDesc(){
  if (type <= 0) {
    return "";
  }
 else   if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
    return "redis-cluster";
  }
 else   if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
    return "redis-sentinel";
  }
 else   if (type == ConstUtils.CACHE_REDIS_STANDALONE) {
    return "redis-standalone";
  }
  return "";
}
