/** 
 * Create a new read/write access object with the given default timeout.
 * @param defaultTimeout the default maximum amount of time to wait for access (must not be negative, 0 == infinite)
 * @return a new read/write access object
 */
static ReadWriteAccess create(Duration defaultTimeout){
  return new DefaultReadWriteAccess(defaultTimeout);
}
