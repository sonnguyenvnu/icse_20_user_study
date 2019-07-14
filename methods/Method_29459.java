public static boolean isRedisSentinel(int type){
  if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
    return true;
  }
  return false;
}
