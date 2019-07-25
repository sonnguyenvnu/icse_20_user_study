/** 
 * Create a group of event processors to be used as a dependency.
 * @param processors the event processors, previously set up with {@link #handleEventsWith(com.alipay.disruptor.EventProcessor)}, that will form the barrier for subsequent handlers or processors.
 * @return an {@link EventHandlerGroup} that can be used to setup a {@link SequenceBarrier} over the specified event processors.
 * @see #after(com.alipay.disruptor.EventHandler[])
 */
public EventHandlerGroup<T> after(final EventProcessor... processors){
  for (  final EventProcessor processor : processors) {
    consumerRepository.add(processor);
  }
  return new EventHandlerGroup<T>(this,consumerRepository,Util.getSequencesFor(processors));
}
