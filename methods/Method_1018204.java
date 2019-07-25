public void refresh(){
  if (isRefreshing.compareAndSet(false,true)) {
    log.info("REFRESHING DATASOURCE for {} ",propertyPrefix);
    boolean userImpersonationEnabled=Boolean.valueOf(env.getProperty("hive.userImpersonation.enabled"));
    if (userImpersonationEnabled && propertyPrefix.equals("hive.datasource")) {
      if (SecurityContextHolder.getContext().getAuthentication() != null) {
        String currentUser=SecurityContextHolder.getContext().getAuthentication().getName();
        DataSource dataSource=create(true,currentUser);
        datasources.put(currentUser,dataSource);
      }
    }
 else {
      DataSource dataSource=create(false,null);
      datasources.put(DEFAULT_DATASOURCE_NAME,dataSource);
    }
    isRefreshing.set(false);
  }
}
