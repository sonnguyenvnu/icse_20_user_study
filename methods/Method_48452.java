private void checkAndOverwriteTransactionLogConfiguration(Configuration combinedConfig,ModifiableConfiguration overwrite,StoreFeatures storeFeatures){
  Preconditions.checkArgument(combinedConfig.get(LOG_BACKEND,TRANSACTION_LOG).equals(LOG_BACKEND.getDefaultValue()),"Must use default log backend for transaction log");
  Preconditions.checkArgument(!combinedConfig.has(LOG_SEND_DELAY,TRANSACTION_LOG) || combinedConfig.get(LOG_SEND_DELAY,TRANSACTION_LOG).isZero(),"Send delay must be 0 for transaction log.");
  overwrite.set(LOG_SEND_DELAY,Duration.ZERO,TRANSACTION_LOG);
  if (!combinedConfig.has(LOG_STORE_TTL,TRANSACTION_LOG) && TTLKCVSManager.supportsAnyTTL(storeFeatures)) {
    overwrite.set(LOG_STORE_TTL,TRANSACTION_LOG_DEFAULT_TTL,TRANSACTION_LOG);
  }
}
