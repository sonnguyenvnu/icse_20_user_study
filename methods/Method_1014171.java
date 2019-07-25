/** 
 * Write out any outstanding data. <p> This creates the backup copy at the same time as writing the database file. This avoids having to either rename the file later (which may leave a small window for there to be no file if the system crashes during the write process), or to copy the file when writing the backup copy (which would require a read and write, and is thus slower).
 */
public synchronized void flush(){
  if (commitTimerTask != null) {
    commitTimerTask.cancel();
    commitTimerTask=null;
  }
  if (dirty) {
    String json=internalMapper.toJson(map);
synchronized (map) {
      writeDatabaseFile(file,json);
      writeDatabaseFile(new File(file.getParent() + File.separator + BACKUP_EXTENSION,System.currentTimeMillis() + SEPARATOR + file.getName()),json);
      cleanupBackups();
      deferredSince=0;
      dirty=false;
    }
  }
}
