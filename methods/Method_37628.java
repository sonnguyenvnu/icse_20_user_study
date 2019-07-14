/** 
 * Registers  {@link jodd.io.watch.DirWatcherEvent consumer}.
 */
public void register(final Consumer<DirWatcherEvent> dirWatcherListener){
  listeners.add(dirWatcherListener);
}
