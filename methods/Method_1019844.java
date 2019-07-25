/** 
 * Set up batch handlers to consume events from the ring buffer. These handlers will only process events after every  {@link EventProcessor} in this group has processed the event.<p>This method is generally used as part of a chain. For example if the handler <code>A</code> must process events before handler <code>B</code>:</p> <pre><code>dw.handleEventsWith(A).then(B);</code></pre>
 * @param handlers the batch handlers that will process events.
 * @return a {@link EventHandlerGroup} that can be used to set up a event processor barrier over the created event processors.
 */
public EventHandlerGroup<T> then(final EventHandler<? super T>... handlers){
  return handleEventsWith(handlers);
}
