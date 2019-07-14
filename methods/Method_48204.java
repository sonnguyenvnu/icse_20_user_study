private void setupTimestampProvider(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration,KeyColumnValueStoreManager storeManager){
  if (!localBasicConfiguration.has(TIMESTAMP_PROVIDER)) {
    StoreFeatures f=storeManager.getFeatures();
    final TimestampProviders backendPreference;
    if (f.hasTimestamps() && null != (backendPreference=f.getPreferredTimestamps())) {
      globalWrite.set(TIMESTAMP_PROVIDER,backendPreference);
      log.info("Set timestamps to {} according to storage backend preference",LoggerUtil.sanitizeAndLaunder(globalWrite.get(TIMESTAMP_PROVIDER)));
    }
 else {
      globalWrite.set(TIMESTAMP_PROVIDER,TIMESTAMP_PROVIDER.getDefaultValue());
      log.info("Set default timestamp provider {}",LoggerUtil.sanitizeAndLaunder(globalWrite.get(TIMESTAMP_PROVIDER)));
    }
  }
 else {
    log.info("Using configured timestamp provider {}",localBasicConfiguration.get(TIMESTAMP_PROVIDER));
  }
}
