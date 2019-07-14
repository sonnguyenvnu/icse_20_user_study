@Override public List<ProviderGroup> getProviderGroups(){
  rLock.lock();
  try {
    List<ProviderGroup> list=new ArrayList<ProviderGroup>();
    list.add(registryGroup);
    list.add(directUrlGroup);
    return list;
  }
  finally {
    rLock.unlock();
  }
}
