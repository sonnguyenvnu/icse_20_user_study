/** 
 * Reports all live allocations. <p>This method can only be used if the  {@link Configuration#DEBUG_MEMORY_ALLOCATOR} option has been set to true.</p>
 * @param report the report callback
 */
public static void memReport(MemoryAllocationReport report){
  DebugAllocator.report(report);
}
