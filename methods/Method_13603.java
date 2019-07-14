@Override public void afterSingletonsInstantiated(){
  sentinelProperties.getDatasource().forEach((dataSourceName,dataSourceProperties) -> {
    try {
      List<String> validFields=dataSourceProperties.getValidField();
      if (validFields.size() != 1) {
        log.error("[Sentinel Starter] DataSource " + dataSourceName + " multi datasource active and won't loaded: " + dataSourceProperties.getValidField());
        return;
      }
      AbstractDataSourceProperties abstractDataSourceProperties=dataSourceProperties.getValidDataSourceProperties();
      abstractDataSourceProperties.setEnv(env);
      abstractDataSourceProperties.preCheck(dataSourceName);
      registerBean(abstractDataSourceProperties,dataSourceName + "-sentinel-" + validFields.get(0) + "-datasource");
    }
 catch (    Exception e) {
      log.error("[Sentinel Starter] DataSource " + dataSourceName + " build error: " + e.getMessage(),e);
    }
  }
);
}
