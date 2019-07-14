public static boolean isRedisCluster(int type){
  if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
    return true;
  }
  return false;
}
