/** 
 * Sets the  {@link ByteBufAllocator}. If not set,  {@link ByteBufAllocator#DEFAULT} is used.
 */
public final B alloc(ByteBufAllocator alloc){
  this.alloc=requireNonNull(alloc,"alloc");
  return self();
}
