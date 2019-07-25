/** 
 * <p>Set up custom event processors to handle events from the ring buffer. The Disruptor will automatically start these processors when  {@link Disruptor#start()} is called.</p><p>This method is generally used as part of a chain. For example if the handler <code>A</code> must process events before handler <code>B</code>:</p>
 * @param eventProcessorFactories the event processor factories to use to create the event processors that will process events.
 * @return a {@link EventHandlerGroup} that can be used to chain dependencies.
 */
public EventHandlerGroup<T> then(final EventProcessorFactory<T>... eventProcessorFactories){
  return handleEventsWith(eventProcessorFactories);
}
