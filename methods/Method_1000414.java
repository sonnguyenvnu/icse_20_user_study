/** 
 * ????
 * @param transLevel ????
 * @see java.sql.Connection
 * @return ???
 * @throws DaoException ?????????
 */
public NutTxDao begin(int transLevel) throws DaoException {
  if (this.conn != null)   throw new DaoException("NutTxDao has been begined!!");
  id=R.UU32();
  if (debug)   log.debugf("begin level=%d id=%s",transLevel,id);
  try {
    this.conn=dataSource.getConnection();
    this.conn.setTransactionIsolation(transLevel);
    if (this.conn.getAutoCommit() == true) {
      this.conn.setAutoCommit(false);
      _autoCommit=true;
    }
    setSavepoint(id);
  }
 catch (  SQLException e) {
    throw new DaoException(e);
  }
  return this;
}
