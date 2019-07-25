/** 
 * Unregisters all handler methods on a registered  {@code object}.
 * @param object  object whose handler methods should be unregistered.
 * @throws IllegalArgumentException if the object was not previously registered.
 */
public void unregister(Object object){
  unsubscribeAll(finder.findAllSubscribers(object));
}
