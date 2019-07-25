/** 
 * <p> Register an additional  {@link RetryReadListener}. </p>
 * @param listener the {@link RetryReadListener} to register
 */
public void register(RetryReadListener listener){
  listeners.add(listener);
}
