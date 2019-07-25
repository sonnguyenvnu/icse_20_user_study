/** 
 * Close the database.
 * @param fromShutdownHook true if this method is called from the shutdownhook
 */
void close(boolean fromShutdownHook){
  DbException b=backgroundException.getAndSet(null);
  try {
    closeImpl(fromShutdownHook);
  }
 catch (  Throwable t) {
    if (b != null) {
      t.addSuppressed(b);
    }
    throw t;
  }
  if (b != null) {
    throw DbException.get(b.getErrorCode(),b,b.getMessage());
  }
}
