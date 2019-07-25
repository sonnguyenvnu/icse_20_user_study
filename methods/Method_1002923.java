/** 
 * Requests up to the given number of messages from the call to be delivered to {@link Listener#messageRead(ByteBufOrStream)}. No additional messages will be delivered. <p>If  {@link #close()} has been called, this method will have no effect.
 * @param numMessages the requested number of messages to be delivered to the listener.
 */
public void request(int numMessages){
  checkArgument(numMessages > 0,"numMessages must be > 0");
  if (isClosed()) {
    return;
  }
  pendingDeliveries+=numMessages;
  deliver();
}
