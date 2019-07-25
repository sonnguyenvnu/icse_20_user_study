/** 
 * Get the current request, which is specific to the current thread.
 * @return the current request
 */
public static Request request(){
  return threadLocal.get();
}
