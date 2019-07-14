/** 
 * Clears the  {@link #waiters} list and returns the most recently added value. 
 */
private Waiter clearWaiters(){
  Waiter head;
  do {
    head=waiters;
  }
 while (!ATOMIC_HELPER.casWaiters(this,head,Waiter.TOMBSTONE));
  return head;
}
