/** 
 * Returns newly created and started timer that repeats and has the specified delay, initial delay and action listener.
 * @param useDaemonThread whether should use daemon thread instead of user one or not
 * @param name            thread name
 * @param delay           delay between timer cycles in milliseconds
 * @param listener        action listener
 * @return newly created and started timer
 */
public static WebTimer repeat(final boolean useDaemonThread,final String name,final long delay,final ActionListener listener){
  return repeat(useDaemonThread,name,delay,delay,listener);
}
