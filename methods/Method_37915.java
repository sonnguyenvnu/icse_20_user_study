/** 
 * Wraps a task into a callable that returns  {@code null}.
 */
public default Callable toCallable(){
  return () -> {
    run();
    return null;
  }
;
}
