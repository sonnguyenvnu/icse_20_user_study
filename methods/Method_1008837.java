/** 
 * Unblocks all threads and runs all listeners. 
 */
private static void complete(AbstractFuture<?> future){
  Listener next=null;
  outer:   while (true) {
    future.releaseWaiters();
    future.afterDone();
    next=future.clearListeners(next);
    future=null;
    while (next != null) {
      Listener curr=next;
      next=next.next;
      Runnable task=curr.task;
      if (task instanceof SetFuture) {
        SetFuture<?> setFuture=(SetFuture<?>)task;
        future=setFuture.owner;
        if (future.value == setFuture) {
          Object valueToSet=getFutureValue(setFuture.future);
          if (ATOMIC_HELPER.casValue(future,setFuture,valueToSet)) {
            continue outer;
          }
        }
      }
 else {
        executeListener(task,curr.executor);
      }
    }
    break;
  }
}
