/** 
 * Generate a resource and a Perhaps based on that resource and then dispose that resource eagerly when the Perhaps terminates or the downstream cancels the sequence.
 * @param < T > the value type
 * @param < R > the resource type
 * @param resourceSupplier the callback to get a resource for each subscriber
 * @param sourceSupplier the function that returns a Perhaps for the generated resource
 * @param disposer the consumer of the resource once the upstream terminates or thedownstream cancels
 * @param eager if true, the resource is disposed before the terminal event is emittedif false, the resource is disposed after the terminal event has been emitted
 * @return the new Perhaps instance
 */
public static <T,R>Perhaps<T> using(Supplier<R> resourceSupplier,Function<? super R,? extends Perhaps<? extends T>> sourceSupplier,Consumer<? super R> disposer,boolean eager){
  ObjectHelper.requireNonNull(resourceSupplier,"resourceSupplier is null");
  ObjectHelper.requireNonNull(sourceSupplier,"sourceSupplier is null");
  ObjectHelper.requireNonNull(disposer,"disposer is null");
  return onAssembly(new PerhapsUsing<T,R>(resourceSupplier,sourceSupplier,disposer,eager));
}
