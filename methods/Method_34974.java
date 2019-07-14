/** 
 * {@inheritDoc}
 * @since 10.0
 */
@Override public void addListener(@Nonnull Runnable listener,@Nonnull Executor executor){
  Preconditions.checkNotNull(listener,"Runnable was null.");
  Preconditions.checkNotNull(executor,"Executor was null.");
  Listener oldHead=listeners;
  if (oldHead != Listener.TOMBSTONE) {
    Listener newNode=new Listener(listener,executor);
    do {
      newNode.next=oldHead;
      if (ATOMIC_HELPER.casListeners(this,oldHead,newNode)) {
        return;
      }
      oldHead=listeners;
    }
 while (oldHead != Listener.TOMBSTONE);
  }
  executeListener(listener,executor);
}
