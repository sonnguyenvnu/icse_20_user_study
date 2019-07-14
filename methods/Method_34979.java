/** 
 * Clears the  {@link #listeners} list and returns the most recently added value. 
 */
private Listener clearListeners(){
  Listener head;
  do {
    head=listeners;
  }
 while (!ATOMIC_HELPER.casListeners(this,head,Listener.TOMBSTONE));
  return head;
}
