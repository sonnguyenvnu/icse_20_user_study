/** 
 * {@inheritDoc}
 */
public DbSession beginTransaction(final JtxTransactionMode jtxMode,final boolean active){
  DbSession session=new DbSession(connectionProvider);
  if (active) {
    log.debug("begin jtx");
    session.beginTransaction(JtxDbUtil.convertToDbMode(jtxMode));
  }
  return session;
}
