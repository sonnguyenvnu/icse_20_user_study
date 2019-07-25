/** 
 * Observe a promise list as an observable stream.
 * @param promise
 * @param < T >
 * @return
 */
public static <T,I extends Iterable<T>>Observable<T> observe(Promise<I> promise){
  return Observable.merge(single(promise).toObservable().map(Observable::fromIterable));
}
