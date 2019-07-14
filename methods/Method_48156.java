public static KeyColumnValueStoreManager getStorageManager(Configuration storageConfig){
  StoreManager manager=getImplementationClass(storageConfig,storageConfig.get(STORAGE_BACKEND),StandardStoreManager.getAllManagerClasses());
  if (manager instanceof OrderedKeyValueStoreManager) {
    manager=new OrderedKeyValueStoreManagerAdapter((OrderedKeyValueStoreManager)manager,ImmutableMap.of(EDGESTORE_NAME,8,EDGESTORE_NAME + LOCK_STORE_SUFFIX,8,storageConfig.get(IDS_STORE_NAME),8));
  }
  Preconditions.checkArgument(manager instanceof KeyColumnValueStoreManager,"Invalid storage manager: %s",manager.getClass());
  return (KeyColumnValueStoreManager)manager;
}
