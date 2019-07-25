/** 
 * Set the state of the  {@link ByteBufAllocator} metric collector.
 * @param enabled True if {@link ByteBufAllocator} metrics should be collected. False otherwise
 * @return {@code this}
 */
public ByteBufAllocatorConfig enable(boolean enabled){
  this.enabled=enabled;
  return this;
}
