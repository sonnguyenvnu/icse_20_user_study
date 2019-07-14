/** 
 * Set the refresh after write in nanoseconds.
 * @param refreshAfterWriteNanos the duration in nanoseconds
 */
public void setRefreshAfterWrite(OptionalLong refreshAfterWriteNanos){
  this.refreshAfterWriteNanos=refreshAfterWriteNanos.isPresent() ? refreshAfterWriteNanos.getAsLong() : null;
}
