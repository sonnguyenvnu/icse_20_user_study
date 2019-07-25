/** 
 * Returns  {@code true} if the {@link Set} for the given{@link DeviceProtocolInfoSource} contains the specified element.
 * @param type the {@link DeviceProtocolInfoSource} type to check.
 * @param protocolInfo the element whose presence is to be tested.
 * @return {@code true} if the {@link Set} for the given{@link DeviceProtocolInfoSource} contains the specified element,{@code false} otherwise.
 */
public boolean contains(DeviceProtocolInfoSource<?> type,ProtocolInfo protocolInfo){
  setsLock.readLock().lock();
  try {
    SortedSet<ProtocolInfo> set=protocolInfoSets.get(type);
    return set == null ? false : set.contains(protocolInfo);
  }
  finally {
    setsLock.readLock().unlock();
  }
}
