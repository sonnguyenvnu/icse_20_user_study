/** 
 * Sets the  {@link PriorityTaskManager} to use.
 * @param priorityTaskManager The {@link PriorityTaskManager}.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setPriorityTaskManager(PriorityTaskManager priorityTaskManager){
  Assertions.checkState(!buildCalled);
  this.priorityTaskManager=priorityTaskManager;
  return this;
}
