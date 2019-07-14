/** 
 * Returns the refresh after write in nanoseconds.
 * @return the duration in nanoseconds
 */
public OptionalLong getRefreshAfterWrite(){
  return (refreshAfterWriteNanos == null) ? OptionalLong.empty() : OptionalLong.of(refreshAfterWriteNanos);
}
