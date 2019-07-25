/** 
 * Flush all changes and open a new transaction log.
 */
public void checkpoint(){
  if (persistent) {
synchronized (this) {
      if (pageStore != null) {
        pageStore.checkpoint();
      }
    }
    if (store != null) {
      store.flush();
    }
  }
  getTempFileDeleter().deleteUnused();
}
