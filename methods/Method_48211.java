private boolean isManagedOverwritesAllowed(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration){
  if (localBasicConfiguration.has(ALLOW_STALE_CONFIG)) {
    return localBasicConfiguration.get(ALLOW_STALE_CONFIG);
  }
 else   if (globalWrite.has(ALLOW_STALE_CONFIG)) {
    return globalWrite.get(ALLOW_STALE_CONFIG);
  }
  return ALLOW_STALE_CONFIG.getDefaultValue();
}
