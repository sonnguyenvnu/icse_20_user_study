/** 
 * Add subscribers disruptor consumer executor.
 * @param subscribers the subscribers
 * @return the disruptor consumer executor
 */
public AbstractDisruptorConsumerExecutor addSubscribers(final Set<ExecutorSubscriber> subscribers){
  subscribers.forEach(this::addSubscribers);
  return this;
}
