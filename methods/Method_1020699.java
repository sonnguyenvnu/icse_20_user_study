/** 
 * Caches the value or error event of the upstream Perhaps and relays/replays it to Subscribers.
 * @return the new Perhaps instance
 * @since 0.14.1
 */
public final Perhaps<T> cache(){
  return onAssembly(new PerhapsCache<T>(this));
}
