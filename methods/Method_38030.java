/** 
 * Creates new daemon thread factory.
 */
public static ThreadFactory daemonThreadFactory(final String name,final int priority){
  return new ThreadFactory(){
    @Override public Thread newThread(    final Runnable r){
      Thread thread=new Thread(r);
      thread.setName(name + '-' + count.incrementAndGet());
      thread.setDaemon(true);
      thread.setPriority(priority);
      return thread;
    }
  }
;
}
