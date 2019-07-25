/** 
 * In the processing of the  {@link java.util.concurrent.Future}s passed, nulls are <em>not</em> passed to the delegate since they are considered filtered out by the  {@link org.springframework.batch.integration.async.AsyncItemProcessor}'s delegated  {@link org.springframework.batch.item.ItemProcessor}.  If the unwrapping of the  {@link Future} results in an {@link ExecutionException}, that will be unwrapped and the cause will be thrown.
 * @param items {@link java.util.concurrent.Future}s to be unwrapped and passed to the delegate
 * @throws Exception The exception returned by the Future if one was thrown
 */
public void write(List<? extends Future<T>> items) throws Exception {
  List<T> list=new ArrayList<>();
  for (  Future<T> future : items) {
    try {
      T item=future.get();
      if (item != null) {
        list.add(future.get());
      }
    }
 catch (    ExecutionException e) {
      Throwable cause=e.getCause();
      if (cause != null && cause instanceof Exception) {
        logger.debug("An exception was thrown while processing an item",e);
        throw (Exception)cause;
      }
 else {
        throw e;
      }
    }
  }
  delegate.write(list);
}
