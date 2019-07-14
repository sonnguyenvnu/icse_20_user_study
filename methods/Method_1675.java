/** 
 * Start producing results for given context. Provided consumer is notified whenever progress is made (new value is ready or error occurs).
 * @param consumer
 * @param context
 */
public void produceResults(Consumer<T> consumer,ProducerContext context){
  consumer.onNewResult((T)null,Consumer.IS_LAST);
}
