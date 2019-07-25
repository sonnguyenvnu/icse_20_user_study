public static HiveSyncConfig copy(HiveSyncConfig cfg){
  HiveSyncConfig newConfig=new HiveSyncConfig();
  newConfig.basePath=cfg.basePath;
  newConfig.assumeDatePartitioning=cfg.assumeDatePartitioning;
  newConfig.databaseName=cfg.databaseName;
  newConfig.hivePass=cfg.hivePass;
  newConfig.hiveUser=cfg.hiveUser;
  newConfig.partitionFields=cfg.partitionFields;
  newConfig.partitionValueExtractorClass=cfg.partitionValueExtractorClass;
  newConfig.jdbcUrl=cfg.jdbcUrl;
  newConfig.tableName=cfg.tableName;
  return newConfig;
}
