/** 
 * Sets the message type forwarded to  {@link Target#handleMessage(int,Object)}.
 * @param messageType The message type.
 * @return This message.
 * @throws IllegalStateException If {@link #send()} has already been called.
 */
public PlayerMessage setType(int messageType){
  Assertions.checkState(!isSent);
  this.type=messageType;
  return this;
}
