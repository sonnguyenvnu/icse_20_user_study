@Modified protected void modified(Map<String,Object> config){
  for (  PackageType packageType : PackageType.values()) {
    Object inclusionCfg=config.get(packageType.configKey);
    if (inclusionCfg != null) {
      packageTypeInclusions.put(packageType.typeName,inclusionCfg.toString().equals(Boolean.TRUE.toString()));
    }
  }
  Object cfgMaturityLevel=config.get("maturity");
  if (cfgMaturityLevel != null) {
    try {
      this.maturityLevel=Integer.valueOf(cfgMaturityLevel.toString());
    }
 catch (    NumberFormatException e) {
      logger.warn("Ignoring invalid value '{}' for configuration parameter '{}'",cfgMaturityLevel.toString(),"maturity");
    }
  }
}
