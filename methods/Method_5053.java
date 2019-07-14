/** 
 * Sets the message payload forwarded to  {@link Target#handleMessage(int,Object)}.
 * @param payload The message payload.
 * @return This message.
 * @throws IllegalStateException If {@link #send()} has already been called.
 */
public PlayerMessage setPayload(@Nullable Object payload){
  Assertions.checkState(!isSent);
  this.payload=payload;
  return this;
}
