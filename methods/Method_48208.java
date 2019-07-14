private void checkJanusGraphVersion(ModifiableConfiguration globalWrite,BasicConfiguration localBasicConfiguration,KCVSConfiguration keyColumnValueStoreConfiguration,ModifiableConfiguration overwrite){
  if (globalWrite.get(INITIAL_JANUSGRAPH_VERSION) == null) {
    log.info("JanusGraph version has not been initialized");
    CompatibilityValidator.validateBackwardCompatibilityWithTitan(globalWrite.get(TITAN_COMPATIBLE_VERSIONS),localBasicConfiguration.get(IDS_STORE_NAME));
    setTitanIDStoreNameIfKeystoreNotExists(keyColumnValueStoreConfiguration,overwrite);
  }
}
