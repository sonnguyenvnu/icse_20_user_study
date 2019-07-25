/** 
 * Re-reads file or folder permissions in case they have changed.
 */
public void refresh(){
  lock.writeLock().lock();
  try {
    flags.clear();
    readChecked=false;
    writeChecked=false;
    executeChecked=false;
    lastCause=null;
  }
  finally {
    lock.writeLock().unlock();
  }
}
