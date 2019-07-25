/** 
 * <p> Register an additional  {@link RetryWriteListener}. </p>
 * @param listener the {@link RetryWriteListener} to register
 */
public void register(RetryWriteListener listener){
  listeners.add(listener);
}
