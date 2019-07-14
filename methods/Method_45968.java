/** 
 * ??????????
 * @param configLoader ?????
 */
public static void unRegisterExternalConfigLoader(ExternalConfigLoader configLoader){
  wLock.lock();
  try {
    CONFIG_LOADERS.remove(configLoader);
    Collections.sort(CONFIG_LOADERS,new OrderedComparator<ExternalConfigLoader>());
  }
  finally {
    wLock.unlock();
  }
}
