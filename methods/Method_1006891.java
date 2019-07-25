/** 
 * <p> Register an additional  {@link RetryProcessListener}. </p>
 * @param listener the {@link RetryProcessListener} to register
 */
public void register(RetryProcessListener listener){
  listeners.add(listener);
}
