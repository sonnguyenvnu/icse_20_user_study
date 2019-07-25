/** 
 * Clients that need to register a top-level  {@code Watcher} should do so using this method.  Theregistered  {@code watcher} will remain registered across re-connects and session expirationevents.
 * @param watcher the {@code Watcher to register}
 */
public void register(Watcher watcher){
  watchers.add(watcher);
}
