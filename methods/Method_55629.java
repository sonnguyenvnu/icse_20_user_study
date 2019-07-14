/** 
 * Reports aggregates for the live allocations. <p>This method can only be used if the  {@link Configuration#DEBUG_MEMORY_ALLOCATOR} option has been set to true.</p>
 * @param report            the report callback
 * @param groupByStackTrace how to aggregate the reported allocations
 * @param groupByThread     if the reported allocations should be grouped by thread
 */
public static void memReport(MemoryAllocationReport report,Aggregate groupByStackTrace,boolean groupByThread){
  DebugAllocator.report(report,groupByStackTrace,groupByThread);
}
