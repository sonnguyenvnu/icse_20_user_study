/** 
 * Sets the hysteresis buffer used to prevent repeated format switching.
 * @param hysteresisBufferMs The offset the current duration of buffered media must deviate fromthe ideal duration of buffered media for the currently selected format, before the selected format is changed. This value must be smaller than  {@code maxBufferMs - minBufferMs}.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setHysteresisBufferMs(int hysteresisBufferMs){
  Assertions.checkState(!buildCalled);
  this.hysteresisBufferMs=hysteresisBufferMs;
  return this;
}
