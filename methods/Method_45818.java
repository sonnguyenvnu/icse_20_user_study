@Override public ProviderGroup getProviderGroup(String groupName){
  rLock.lock();
  try {
    return RpcConstants.ADDRESS_DIRECT_GROUP.equals(groupName) ? directUrlGroup : registryGroup;
  }
  finally {
    rLock.unlock();
  }
}
