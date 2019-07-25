/** 
 * Clients can attempt to unregister a top-level  {@code Watcher} that has previously beenregistered.
 * @param watcher the {@code Watcher} to unregister as a top-level, persistent watch
 * @return whether the given {@code Watcher} was found and removed from the active set
 */
public boolean unregister(Watcher watcher){
  return watchers.remove(watcher);
}
