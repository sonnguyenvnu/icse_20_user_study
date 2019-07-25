/** 
 * Returns a THROTTLE decision, with the individual node-level decisions that comprised the final THROTTLE decision if in explain mode.
 */
public static AllocateUnassignedDecision throttle(@Nullable List<NodeAllocationResult> decisions){
  if (decisions != null) {
    return new AllocateUnassignedDecision(AllocationStatus.DECIDERS_THROTTLED,null,null,decisions,false,0L,0L);
  }
 else {
    return getCachedDecision(AllocationStatus.DECIDERS_THROTTLED);
  }
}
