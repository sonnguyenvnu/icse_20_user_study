/** 
 * decrease counter
 * @param hasExportedInCurrent
 */
private void decrementCounter(Map<String,Boolean> hasExportedInCurrent){
  for (  Map.Entry<String,Boolean> entry : hasExportedInCurrent.entrySet()) {
    String protocol=entry.getKey();
    String key=providerConfig.buildKey() + ":" + protocol;
    AtomicInteger cnt=EXPORTED_KEYS.get(key);
    if (cnt != null && cnt.get() > 0) {
      cnt.decrementAndGet();
    }
  }
}
