/** 
 * Wraps the given listener with  {@link SynchronizedRunListener} ifit is not annotated with  {@link RunListener.ThreadSafe}.
 */
RunListener wrapIfNotThreadSafe(RunListener listener){
  return listener.getClass().isAnnotationPresent(RunListener.ThreadSafe.class) ? listener : new SynchronizedRunListener(listener,this);
}
