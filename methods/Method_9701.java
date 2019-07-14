/** 
 * Loads domains.
 */
public void loadDomains(){
  LOCK.writeLock().lock();
  try {
    DOMAINS.clear();
    DOMAINS.addAll(domainQueryService.getMostTagNaviDomains(Integer.MAX_VALUE));
  }
  finally {
    LOCK.writeLock().unlock();
  }
}
