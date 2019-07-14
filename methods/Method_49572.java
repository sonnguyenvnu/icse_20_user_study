public static BiMap<String,String> createShortCfMap(Configuration config){
  return ImmutableBiMap.<String,String>builder().put(INDEXSTORE_NAME,"g").put(INDEXSTORE_NAME + LOCK_STORE_SUFFIX,"h").put(config.get(IDS_STORE_NAME),"i").put(EDGESTORE_NAME,"e").put(EDGESTORE_NAME + LOCK_STORE_SUFFIX,"f").put(SYSTEM_PROPERTIES_STORE_NAME,"s").put(SYSTEM_PROPERTIES_STORE_NAME + LOCK_STORE_SUFFIX,"t").put(SYSTEM_MGMT_LOG_NAME,"m").put(SYSTEM_TX_LOG_NAME,"l").build();
}
