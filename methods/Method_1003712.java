/** 
 * Set the state of detailed metric collectrion for the  {@link ByteBufAllocator} metric collector.
 * @param detailed The state of detailed metric collection for the {@link ByteBufAllocator} metric collector.
 * @return {@code this}
 */
public ByteBufAllocatorConfig detail(boolean detailed){
  this.detailed=detailed;
  return this;
}
