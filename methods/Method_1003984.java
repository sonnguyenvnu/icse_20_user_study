/** 
 * Attempts to join the partition group and claim a slice.  When successful, a predicate is returned that can be used to test whether or not an item belongs to this partition.  The predicate is dynamic such that as the group is further partitioned or partitions merge the predicate will claim a narrower or wider swath of the partition space respectively.  Partition creation and merging is not instantaneous and clients should expect independent partitions to claim ownership of some items when partition membership is in flux.  It is only in the steady state that a client should expect independent partitions to divide the partition space evenly and without overlap. <p>TODO(John Sirois): consider adding a version with a global timeout for the join operation.
 * @return the partition representing the slice of the partition group this member can claim
 * @throws JoinException if there was a problem joining the partition group
 * @throws InterruptedException if interrupted while waiting to join the partition group
 */
public final Partition join() throws JoinException, InterruptedException {
  final Membership membership=group.join();
  try {
    group.watch(createGroupChangeListener(membership));
  }
 catch (  WatchException e) {
    membership.cancel();
    throw new JoinException("Problem establishing watch on group after joining it",e);
  }
  return new Partition(){
    @Override public boolean isMember(    long value){
      return (value % groupSize) == groupIndex;
    }
    @Override public int getNumPartitions(){
      return groupSize;
    }
    @Override public String getGroupPath(){
      return membership.getGroupPath();
    }
    @Override public String getMemberId(){
      return membership.getMemberId();
    }
    @Override public String getMemberPath(){
      return membership.getMemberPath();
    }
    @Override public byte[] updateMemberData() throws UpdateException {
      return membership.updateMemberData();
    }
    @Override public void cancel() throws JoinException {
      membership.cancel();
    }
  }
;
}
