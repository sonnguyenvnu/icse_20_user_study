private boolean isUpgradeAllowed(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration){
  if (localBasicConfiguration.has(ALLOW_UPGRADE)) {
    return localBasicConfiguration.get(ALLOW_UPGRADE);
  }
 else   if (globalWrite.has(ALLOW_UPGRADE)) {
    return globalWrite.get(ALLOW_UPGRADE);
  }
  return ALLOW_UPGRADE.getDefaultValue();
}
