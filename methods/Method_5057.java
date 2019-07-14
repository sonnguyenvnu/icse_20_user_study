/** 
 * Sends the message. If the target throws an  {@link ExoPlaybackException} then it is propagatedout of the player as an error using  {@link Player.EventListener#onPlayerError(ExoPlaybackException)}.
 * @return This message.
 * @throws IllegalStateException If this message has already been sent.
 */
public PlayerMessage send(){
  Assertions.checkState(!isSent);
  if (positionMs == C.TIME_UNSET) {
    Assertions.checkArgument(deleteAfterDelivery);
  }
  isSent=true;
  sender.sendMessage(this);
  return this;
}
