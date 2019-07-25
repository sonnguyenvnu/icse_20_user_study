/** 
 * ??DaoExecutor??????,????????????!! ??????????????.
 */
public void invoke(Connection conn) throws Exception {
  for (  DaoStatement st : sts) {
    if (st == null) {
      if (log.isInfoEnabled())       log.info("Found a null DaoStatement(SQL), ingore it ~~");
      continue;
    }
    current=0;
    daoStatement=st;
    this.connection=conn;
    doChain();
  }
}
