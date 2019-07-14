/** 
 * Sets a position in a window at which the message will be delivered.
 * @param windowIndex The index of the window at which the message will be sent.
 * @param positionMs The position in the window with index {@code windowIndex} at which themessage will be sent, in milliseconds.
 * @return This message.
 * @throws IllegalSeekPositionException If the timeline returned by {@link #getTimeline()} is notempty and the provided window index is not within the bounds of the timeline.
 * @throws IllegalStateException If {@link #send()} has already been called.
 */
public PlayerMessage setPosition(int windowIndex,long positionMs){
  Assertions.checkState(!isSent);
  Assertions.checkArgument(positionMs != C.TIME_UNSET);
  if (windowIndex < 0 || (!timeline.isEmpty() && windowIndex >= timeline.getWindowCount())) {
    throw new IllegalSeekPositionException(timeline,windowIndex,positionMs);
  }
  this.windowIndex=windowIndex;
  this.positionMs=positionMs;
  return this;
}
