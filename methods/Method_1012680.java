/** 
 * Removes all elements in the  {@link Set} for the given{@link DeviceProtocolInfoSource}.
 * @param type The {@link DeviceProtocolInfoSource} type to clear.
 */
public void clear(DeviceProtocolInfoSource<?> type){
  setsLock.writeLock().lock();
  try {
    SortedSet<ProtocolInfo> set=protocolInfoSets.get(type);
    if (set != null) {
      set.clear();
      updateImageProfiles();
    }
  }
  finally {
    setsLock.writeLock().unlock();
  }
}
