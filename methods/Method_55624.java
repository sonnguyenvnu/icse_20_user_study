/** 
 * The standard C free function. <p>A block of memory previously allocated by a call to  {@link #memAlloc},  {@link #memCalloc} or {@link #memRealloc} is deallocated, making it availableagain for further allocations.</p>
 * @param ptr pointer to a memory block previously allocated with {@link #memAlloc},  {@link #memCalloc} or {@link #memRealloc}. If  {@code ptr} does notpoint to a block of memory allocated with the above functions, it causes undefined behavior. If  {@code ptr} is a {@link #NULL} pointer, thefunction does nothing.
 */
public static void memFree(@Nullable Buffer ptr){
  if (ptr != null) {
    nmemFree(UNSAFE.getLong(ptr,ADDRESS));
  }
}
