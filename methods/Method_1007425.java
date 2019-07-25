/** 
 * Refresh.
 * @param timeout the timeout
 */
public void refresh(int timeout){
  if (enabled) {
    scheduler.scheduleCommand(reloadSessionFactoryCommand,timeout);
  }
}
