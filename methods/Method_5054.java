/** 
 * Sets the handler the message is delivered on.
 * @param handler A {@link Handler}.
 * @return This message.
 * @throws IllegalStateException If {@link #send()} has already been called.
 */
public PlayerMessage setHandler(Handler handler){
  Assertions.checkState(!isSent);
  this.handler=handler;
  return this;
}
