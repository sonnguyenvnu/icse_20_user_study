@VisibleForTesting protected void markdown(final AbstractInstanceEvent event){
  final RedisInstanceInfo info=event.getInstance().getRedisInstanceInfo();
  boolean siteReliable=checker.isSiteHealthy(event);
  if (siteReliable) {
    doMarkDown(event);
  }
 else {
    logger.warn("[site-down][not-mark-down] {}",info);
  }
}
