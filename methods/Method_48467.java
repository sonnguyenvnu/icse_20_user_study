public boolean isUpgradeAllowed(String name){
  return configuration.get(ALLOW_UPGRADE) && JanusGraphConstants.UPGRADEABLE_FIXED.contains(name);
}
