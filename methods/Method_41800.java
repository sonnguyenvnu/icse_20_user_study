@Override public ToolkitReadWriteLock createLockForKey(K key){
  return toolkitStore.createLockForKey(serializeToString(key));
}
