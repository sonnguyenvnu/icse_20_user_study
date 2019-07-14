@Override public void afterPropertiesSet() throws Exception {
  super.afterPropertiesSet();
  if (cleanupIntervalSeconds == null) {
    cleanupIntervalSeconds=60 * 60;
  }
  if (cleanupIntervalSeconds > 0) {
    scheduler=Executors.newSingleThreadScheduledExecutor();
    Runnable cleanupLogic=new Runnable(){
      public void run(){
        Iterator<Map.Entry<String,OAuthProviderTokenImpl>> entriesIt=tokenStore.entrySet().iterator();
        while (entriesIt.hasNext()) {
          Map.Entry<String,OAuthProviderTokenImpl> entry=entriesIt.next();
          OAuthProviderTokenImpl tokenImpl=entry.getValue();
          if (isExpired(tokenImpl)) {
            entriesIt.remove();
            onTokenRemoved(tokenImpl);
          }
        }
      }
    }
;
    scheduler.scheduleAtFixedRate(cleanupLogic,getAccessTokenValiditySeconds(),cleanupIntervalSeconds,TimeUnit.SECONDS);
  }
}
