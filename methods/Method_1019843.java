/** 
 * Create a new event handler group that combines the handlers in this group with <tt>processors</tt>.
 * @param processors the processors to combine.
 * @return a new EventHandlerGroup combining the existing and new processors into a single dependency group.
 */
public EventHandlerGroup<T> and(final EventProcessor... processors){
  Sequence[] combinedSequences=new Sequence[sequences.length + processors.length];
  for (int i=0; i < processors.length; i++) {
    consumerRepository.add(processors[i]);
    combinedSequences[i]=processors[i].getSequence();
  }
  System.arraycopy(sequences,0,combinedSequences,processors.length,sequences.length);
  return new EventHandlerGroup<T>(disruptor,consumerRepository,combinedSequences);
}
