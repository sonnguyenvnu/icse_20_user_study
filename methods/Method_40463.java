/** 
 * If aggressive assertions are enabled, propages the passed {@link Throwable}, wrapped in an  {@link IndexerException}.
 * @param msg descriptive message; ok to be {@code null}
 * @throws IndexerException
 */
public void handleException(String msg,Throwable cause){
  if (cause instanceof StackOverflowError) {
    logger.log(Level.WARNING,msg,cause);
    return;
  }
  if (aggressiveAssertionsEnabled()) {
    if (msg != null) {
      throw new IndexerException(msg,cause);
    }
    throw new IndexerException(cause);
  }
  if (msg == null)   msg="<null msg>";
  if (cause == null)   cause=new Exception();
  logger.log(Level.WARNING,msg,cause);
}
