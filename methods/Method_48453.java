private void checkAndOverwriteSystemManagementLogConfiguration(Configuration combinedConfig,ModifiableConfiguration overwrite){
  Preconditions.checkArgument(combinedConfig.get(LOG_BACKEND,MANAGEMENT_LOG).equals(LOG_BACKEND.getDefaultValue()),"Must use default log backend for system log");
  Preconditions.checkArgument(!combinedConfig.has(LOG_SEND_DELAY,MANAGEMENT_LOG) || combinedConfig.get(LOG_SEND_DELAY,MANAGEMENT_LOG).isZero(),"Send delay must be 0 for system log.");
  overwrite.set(LOG_SEND_DELAY,Duration.ZERO,MANAGEMENT_LOG);
  Preconditions.checkArgument(!combinedConfig.has(KCVSLog.LOG_KEY_CONSISTENT,MANAGEMENT_LOG) || combinedConfig.get(KCVSLog.LOG_KEY_CONSISTENT,MANAGEMENT_LOG),"Management log must be configured to be key-consistent");
  overwrite.set(KCVSLog.LOG_KEY_CONSISTENT,true,MANAGEMENT_LOG);
  Preconditions.checkArgument(!combinedConfig.has(KCVSLogManager.LOG_FIXED_PARTITION,MANAGEMENT_LOG) || combinedConfig.get(KCVSLogManager.LOG_FIXED_PARTITION,MANAGEMENT_LOG),"Fixed partitions must be enabled for management log");
  overwrite.set(KCVSLogManager.LOG_FIXED_PARTITION,true,MANAGEMENT_LOG);
}
