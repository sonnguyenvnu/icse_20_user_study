default void put(SetRemoteQueryContext<K,R> q){
  SetReplicableEntry<K> entry=q.entry();
  if (entry != null) {
    if (decideOnRemoteModification(entry,q) == ACCEPT) {
      entry.updateOrigin(q.remoteIdentifier(),q.remoteTimestamp());
      if (q.remoteIdentifier() == q.currentNodeIdentifier()) {
        entry.raiseChanged();
      }
 else {
        entry.dropChanged();
      }
    }
  }
 else {
    SetAbsentEntry<K> absentEntry=q.absentEntry();
    assert absentEntry != null;
    if (!(absentEntry instanceof ReplicableEntry) || decideOnRemoteModification((ReplicableEntry)absentEntry,q) == ACCEPT) {
      q.insert(absentEntry);
      entry=q.entry();
      assert entry != null;
      entry.updateOrigin(q.remoteIdentifier(),q.remoteTimestamp());
      if (q.remoteIdentifier() == q.currentNodeIdentifier()) {
        entry.raiseChanged();
      }
 else {
        entry.dropChanged();
      }
    }
 else {
      if (((ReplicableEntry)absentEntry).originIdentifier() == q.remoteIdentifier() && q.remoteIdentifier() != q.currentNodeIdentifier()) {
        ((ReplicableEntry)absentEntry).raiseChangedFor(q.remoteIdentifier());
      }
    }
  }
}
