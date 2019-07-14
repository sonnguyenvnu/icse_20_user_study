/** 
 * Creates a new  {@code MemoryStack} backed by the specified memory region.<p>In the initial state, there is no active stack frame. The  {@link #push} method must be used before any allocations.</p>
 * @param address the backing memory address
 * @param size    the backing memory size
 */
public static MemoryStack ncreate(long address,int size){
  return Configuration.DEBUG_STACK.get(false) ? new DebugMemoryStack(null,address,size) : new MemoryStack(null,address,size);
}
