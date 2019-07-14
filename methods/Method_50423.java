/** 
 * Add subscribers disruptor consumer executor.
 * @param subscriber subscriber?
 * @return the disruptor consumer executor
 */
public AbstractDisruptorConsumerExecutor addSubscribers(final ExecutorSubscriber subscriber){
  subscribers.add(subscriber);
  return this;
}
