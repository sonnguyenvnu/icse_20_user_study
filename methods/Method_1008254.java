/** 
 * Creates a decision for whether to move the shard to a different node to form a better cluster balance.
 */
public static MoveDecision rebalance(Decision canRebalanceDecision,AllocationDecision allocationDecision,@Nullable DiscoveryNode assignedNode,int currentNodeRanking,List<NodeAllocationResult> nodeDecisions){
  return new MoveDecision(null,canRebalanceDecision,allocationDecision,assignedNode,nodeDecisions,currentNodeRanking);
}
