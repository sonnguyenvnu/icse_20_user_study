/** 
 * Trims the pool in response to low-memory states (invoked from MemoryManager) For now, we'll do the simplest thing, and simply clear out the entire pool. We may consider more sophisticated approaches later. In other words, we ignore the memoryTrimType parameter
 * @param memoryTrimType the kind of trimming we want to perform
 */
public void trim(MemoryTrimType memoryTrimType){
  trimToNothing();
}
