public static boolean isRedisDataType(int type){
  if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER || type == ConstUtils.CACHE_REDIS_STANDALONE) {
    return true;
  }
  return false;
}
