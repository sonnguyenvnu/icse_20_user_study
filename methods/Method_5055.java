/** 
 * Sets a position in the current window at which the message will be delivered.
 * @param positionMs The position in the current window at which the message will be sent, inmilliseconds.
 * @return This message.
 * @throws IllegalStateException If {@link #send()} has already been called.
 */
public PlayerMessage setPosition(long positionMs){
  Assertions.checkState(!isSent);
  this.positionMs=positionMs;
  return this;
}
