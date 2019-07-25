/** 
 * Class invariant that should hold before and after every invocation of public methods on this class. As Java lacks implication as a logical operator, many of the invariants are written under the form (!A || B), they should be read as (A implies B) however.
 */
private boolean invariant(){
  assert checkpoints.get(shardAllocationId) != null : "checkpoints map should always have an entry for the current shard";
  assert primaryMode || checkpoints.values().stream().allMatch(lcps -> lcps.localCheckpoint == SequenceNumbers.UNASSIGNED_SEQ_NO || lcps.localCheckpoint == SequenceNumbers.PRE_60_NODE_CHECKPOINT);
  assert primaryMode || checkpoints.entrySet().stream().filter(e -> e.getKey().equals(shardAllocationId) == false).map(Map.Entry::getValue).allMatch(cps -> (cps.globalCheckpoint == SequenceNumbers.UNASSIGNED_SEQ_NO || cps.globalCheckpoint == SequenceNumbers.PRE_60_NODE_CHECKPOINT));
  assert !handoffInProgress || primaryMode;
  assert !primaryMode || checkpoints.get(shardAllocationId).inSync;
  assert !primaryMode || (routingTable != null && replicationGroup != null) : "primary mode but routing table is " + routingTable + " and replication group is " + replicationGroup;
  assert !primaryMode || (routingTable.primaryShard().allocationId().getId().equals(shardAllocationId) || routingTable.primaryShard().allocationId().getRelocationId().equals(shardAllocationId));
  assert !handoffInProgress || pendingInSync.isEmpty() : "entries blocking global checkpoint advancement during relocation handoff: " + pendingInSync;
  assert pendingInSync.isEmpty() || (primaryMode && !handoffInProgress);
  assert !primaryMode || getGlobalCheckpoint() == computeGlobalCheckpoint(pendingInSync,checkpoints.values(),getGlobalCheckpoint()) : "global checkpoint is not up-to-date, expected: " + computeGlobalCheckpoint(pendingInSync,checkpoints.values(),getGlobalCheckpoint()) + " but was: " + getGlobalCheckpoint();
  assert !primaryMode || getGlobalCheckpoint() <= inSyncCheckpointStates(checkpoints,CheckpointState::getLocalCheckpoint,LongStream::min) : "global checkpoint [" + getGlobalCheckpoint() + "] " + "for primary mode allocation ID [" + shardAllocationId + "] " + "more than in-sync local checkpoints [" + checkpoints + "]";
  assert (routingTable == null) == (replicationGroup == null) : "routing table is " + routingTable + " but replication group is " + replicationGroup;
  assert replicationGroup == null || replicationGroup.equals(calculateReplicationGroup()) : "cached replication group out of sync: expected: " + calculateReplicationGroup() + " but was: " + replicationGroup;
  assert routingTable == null || checkpoints.keySet().containsAll(routingTable.getAllAllocationIds()) : "local checkpoints " + checkpoints + " not in-sync with routing table " + routingTable;
  for (  Map.Entry<String,CheckpointState> entry : checkpoints.entrySet()) {
    assert !pendingInSync.contains(entry.getKey()) || !entry.getValue().inSync : "shard copy " + entry.getKey() + " blocks global checkpoint advancement but is in-sync";
  }
  return true;
}
