public static boolean isRedisType(int type){
  if (type == ConstUtils.CACHE_REDIS_SENTINEL || type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER || type == ConstUtils.CACHE_REDIS_STANDALONE) {
    return true;
  }
  return false;
}
