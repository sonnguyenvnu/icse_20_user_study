/** 
 * Sets the buffer duration parameters.
 * @param minBufferMs The minimum duration of media that the player will attempt to ensure isbuffered at all times, in milliseconds.
 * @param maxBufferMs The maximum duration of media that the player will attempt to buffer, inmilliseconds.
 * @param bufferForPlaybackMs The duration of media that must be buffered for playback to start orresume following a user action such as a seek, in milliseconds.
 * @param bufferForPlaybackAfterRebufferMs The default duration of media that must be buffered forplayback to resume after a rebuffer, in milliseconds. A rebuffer is defined to be caused by buffer depletion rather than a user action.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setBufferDurationsMs(int minBufferMs,int maxBufferMs,int bufferForPlaybackMs,int bufferForPlaybackAfterRebufferMs){
  Assertions.checkState(!buildCalled);
  this.minBufferMs=minBufferMs;
  this.maxBufferMs=maxBufferMs;
  this.bufferForPlaybackMs=bufferForPlaybackMs;
  this.bufferForPlaybackAfterRebufferMs=bufferForPlaybackAfterRebufferMs;
  return this;
}
