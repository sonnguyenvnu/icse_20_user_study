/** 
 * Set the clock to use. Should only be set for testing purposes.
 * @param clock The {@link Clock}.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setClock(Clock clock){
  Assertions.checkState(!buildCalled);
  this.clock=clock;
  return this;
}
