/** 
 * Forget a transaction. This method does not have an effect for this database.
 * @param xid the transaction id
 */
@Override public void forget(Xid xid){
  if (isDebugEnabled()) {
    debugCode("forget(" + JdbcXid.toString(xid) + ");");
  }
  prepared=false;
}
