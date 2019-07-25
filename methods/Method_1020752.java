/** 
 * Coalesces items from upstream into a container via a consumer and emits the container if there is a downstream demand, otherwise it keeps coalescing into the same container.
 * @param < T > the upstream value type
 * @param < R > the container and result type
 * @param containerSupplier the function called and should return a fresh container to coalesce into
 * @param coalescer the consumer receiving the current container and upstream item to handle
 * @return the new FlowableTransformer instance
 * @since 0.17.3
 */
public static <T,R>FlowableTransformer<T,R> coalesce(Supplier<R> containerSupplier,BiConsumer<R,T> coalescer){
  return coalesce(containerSupplier,coalescer,Flowable.bufferSize());
}
