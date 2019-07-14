/** 
 * ?????????
 * @param configLoader ?????
 */
public static void registerExternalConfigLoader(ExternalConfigLoader configLoader){
  wLock.lock();
  try {
    CONFIG_LOADERS.add(configLoader);
    Collections.sort(CONFIG_LOADERS,new OrderedComparator<ExternalConfigLoader>());
  }
  finally {
    wLock.unlock();
  }
}
