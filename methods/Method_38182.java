/** 
 * {@inheritDoc}
 */
public void commitTransaction(final DbSession resource){
  if (resource.isTransactionActive()) {
    log.debug("commit jtx");
    resource.commitTransaction();
  }
  resource.closeSession();
}
