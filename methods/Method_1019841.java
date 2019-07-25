/** 
 * <p>Create a group of event handlers to be used as a dependency. For example if the handler <code>A</code> must process events before handler <code>B</code>:</p> <pre><code>dw.after(A).handleEventsWith(B);</code></pre>
 * @param handlers the event handlers, previously set up with {@link #handleEventsWith(com.alipay.disruptor.EventHandler[])}, that will form the barrier for subsequent handlers or processors.
 * @return an {@link EventHandlerGroup} that can be used to setup a dependency barrier over the specified event handlers.
 */
@SuppressWarnings("varargs") public EventHandlerGroup<T> after(final EventHandler<T>... handlers){
  final Sequence[] sequences=new Sequence[handlers.length];
  for (int i=0, handlersLength=handlers.length; i < handlersLength; i++) {
    sequences[i]=consumerRepository.getSequenceFor(handlers[i]);
  }
  return new EventHandlerGroup<T>(this,consumerRepository,sequences);
}
