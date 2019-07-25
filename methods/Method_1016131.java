/** 
 * Returns newly created and started timer that repeats and has the specified delay and action listener.
 * @param delay    delay between timer cycles in milliseconds
 * @param listener action listener
 * @return newly created and started timer
 */
public static WebTimer repeat(final long delay,final ActionListener listener){
  return repeat(defaultThreadName,delay,delay,defaultCyclesLimit,useEdtByDefault,listener);
}
