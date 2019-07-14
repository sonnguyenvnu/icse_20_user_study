/** 
 * Returns the  {@link MemoryAllocator} instance used internally by the explicit memory management API ({@link #memAlloc},  {@link #memFree}, etc).
 * @param tracked whether allocations will be tracked for memory leaks, if {@link Configuration#DEBUG_MEMORY_ALLOCATOR} is enabled.
 * @return the {@link MemoryAllocator} instance
 */
public static MemoryAllocator getAllocator(boolean tracked){
  return tracked ? ALLOCATOR : ALLOCATOR_IMPL;
}
