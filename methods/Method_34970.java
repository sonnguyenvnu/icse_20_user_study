/** 
 * Marks the given node as 'deleted' (null waiter) and then scans the list to unlink all deleted nodes.  This is an O(n) operation in the common case (and O(n^2) in the worst), but we are saved by two things. <ul> <li>This is only called when a waiting thread times out or is interrupted.  Both of which should be rare. <li>The waiters list should be very short. </ul>
 */
private void removeWaiter(@Nonnull Waiter node){
  node.thread=null;
  restart:   while (true) {
    Waiter pred=null;
    Waiter curr=waiters;
    if (curr == Waiter.TOMBSTONE) {
      return;
    }
    Waiter succ;
    while (curr != null) {
      succ=curr.next;
      if (curr.thread != null) {
        pred=curr;
      }
 else       if (pred != null) {
        pred.next=succ;
        if (pred.thread == null) {
          continue restart;
        }
      }
 else       if (!ATOMIC_HELPER.casWaiters(this,curr,succ)) {
        continue restart;
      }
      curr=succ;
    }
    break;
  }
}
