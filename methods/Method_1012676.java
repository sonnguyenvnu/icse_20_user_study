/** 
 * Returns the number of elements of the given {@link DeviceProtocolInfoSource} type. If this contains more than{@link Integer#MAX_VALUE} elements, returns {@link Integer#MAX_VALUE}.
 * @param type the {@link DeviceProtocolInfoSource} type to get the numberof elements for.
 * @return The number of elements in the {@link Set} for {@code type}.
 */
public int size(DeviceProtocolInfoSource<?> type){
  setsLock.readLock().lock();
  try {
    SortedSet<ProtocolInfo> set=protocolInfoSets.get(type);
    return set == null ? 0 : set.size();
  }
  finally {
    setsLock.readLock().unlock();
  }
}
