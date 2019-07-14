/** 
 * Gets domains with the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return domains
 */
public List<JSONObject> getDomains(final int fetchSize){
  LOCK.readLock().lock();
  try {
    if (DOMAINS.isEmpty()) {
      return Collections.emptyList();
    }
    final int end=fetchSize >= DOMAINS.size() ? DOMAINS.size() : fetchSize;
    return JSONs.clone(DOMAINS.subList(0,end));
  }
  finally {
    LOCK.readLock().unlock();
  }
}
