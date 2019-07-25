/** 
 * Stops the configured Logcat dependency and creates a clone to restart using Logcat and LogcatListener configured previously.
 */
public synchronized void restart(){
  Logcat.Listener previousListener=logcat.getListener();
  logcat.stopReading();
  logcat.interrupt();
  logcat=(Logcat)logcat.clone();
  logcat.setListener(previousListener);
  lastNotificationTime=0;
  tracesToNotify.clear();
  logcat.start();
}
