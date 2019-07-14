/** 
 * Blocks until after the message has been delivered or the player is no longer able to deliver the message. <p>Note that this method can't be called if the current thread is the same thread used by the message handler set with  {@link #setHandler(Handler)} as it would cause a deadlock.
 * @return Whether the message was delivered successfully.
 * @throws IllegalStateException If this method is called before {@link #send()}.
 * @throws IllegalStateException If this method is called on the same thread used by the messagehandler set with  {@link #setHandler(Handler)}.
 * @throws InterruptedException If the current thread is interrupted while waiting for the messageto be delivered.
 */
public synchronized boolean blockUntilDelivered() throws InterruptedException {
  Assertions.checkState(isSent);
  Assertions.checkState(handler.getLooper().getThread() != Thread.currentThread());
  while (!isProcessed) {
    wait();
  }
  return isDelivered;
}
