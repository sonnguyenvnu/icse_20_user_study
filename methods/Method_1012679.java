/** 
 * Returns  {@code true} if any of the {@link DeviceProtocolInfoSource}{@link Set}s contains the specified element.
 * @param protocolInfo the element whose presence is to be tested.
 * @return {@code true} if any of the {@link DeviceProtocolInfoSource}{@link Set}s contains the specified element  {@code false}otherwise.
 */
public boolean contains(ProtocolInfo protocolInfo){
  setsLock.readLock().lock();
  try {
    for (    SortedSet<ProtocolInfo> set : protocolInfoSets.values()) {
      if (set != null && set.contains(protocolInfo)) {
        return true;
      }
    }
  }
  finally {
    setsLock.readLock().unlock();
  }
  return false;
}
