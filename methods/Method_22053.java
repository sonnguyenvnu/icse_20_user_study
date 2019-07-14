/** 
 * ????.
 */
public synchronized void pauseJob(){
  try {
    if (!scheduler.isShutdown()) {
      scheduler.pauseAll();
    }
  }
 catch (  final SchedulerException ex) {
    throw new JobSystemException(ex);
  }
}
