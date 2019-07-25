protected static void disable(){
  CacheThreadLocal var=cacheThreadLocal.get();
  var.setEnabledCount(var.getEnabledCount() - 1);
}
