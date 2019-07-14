private void janusGraphVersionsWithDisallowedUpgrade(ModifiableConfiguration globalWrite){
  globalWrite.set(INITIAL_JANUSGRAPH_VERSION,JanusGraphConstants.VERSION);
  globalWrite.set(TITAN_COMPATIBLE_VERSIONS,JanusGraphConstants.VERSION);
  globalWrite.set(INITIAL_STORAGE_VERSION,JanusGraphConstants.STORAGE_VERSION);
  globalWrite.set(ALLOW_UPGRADE,false);
}
