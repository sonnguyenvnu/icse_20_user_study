/** 
 * Returns usable memory.
 */
public final long getAvailableMemory(){
  return runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory();
}
