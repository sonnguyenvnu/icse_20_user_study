/** 
 * Unregister a platform from WorldEdit. <p>If the platform has been chosen for any capabilities, then a new platform will be found.</p>
 * @param platform the platform
 */
public synchronized boolean unregister(Platform platform){
  checkNotNull(platform);
  boolean removed=platforms.remove(platform);
  if (removed) {
    logger.info("Unregistering " + platform.getClass().getCanonicalName() + " from WorldEdit");
    boolean choosePreferred=false;
    Iterator<Entry<Capability,Platform>> it=preferences.entrySet().iterator();
    while (it.hasNext()) {
      Entry<Capability,Platform> entry=it.next();
      if (entry.getValue().equals(platform)) {
        entry.getKey().unload(this,entry.getValue());
        it.remove();
        choosePreferred=true;
      }
    }
    if (choosePreferred) {
      choosePreferred();
    }
  }
  return removed;
}
