/** 
 * Returns the  {@link Executor} that is handling the current {@link Request}.
 */
default Executor executor(){
  return eventLoop();
}
