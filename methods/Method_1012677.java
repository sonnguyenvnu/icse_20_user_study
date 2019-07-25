/** 
 * Returns the total number of elements of all {@link DeviceProtocolInfoSource} types. If the result is greater than{@link Integer#MAX_VALUE} elements, returns {@link Integer#MAX_VALUE}.
 * @return The number of elements.
 */
public int size(){
  long result=0;
  setsLock.readLock().lock();
  try {
    for (    SortedSet<ProtocolInfo> set : protocolInfoSets.values()) {
      if (set != null) {
        result+=set.size();
      }
    }
  }
  finally {
    setsLock.readLock().unlock();
  }
  return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)result;
}
