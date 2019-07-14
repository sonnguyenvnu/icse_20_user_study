/** 
 * Creates new daemon thread factory.
 */
public static ThreadFactory daemonThreadFactory(final String name){
  return daemonThreadFactory(name,Thread.NORM_PRIORITY);
}
