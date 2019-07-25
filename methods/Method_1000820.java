/** 
 * ????
 */
protected void commit(){
  ComboException ce=new ComboException();
  for (  ConnInfo cInfo : list) {
    try {
      cInfo.conn.commit();
      if (cInfo.conn.getTransactionIsolation() != cInfo.oldLevel)       cInfo.conn.setTransactionIsolation(cInfo.oldLevel);
    }
 catch (    SQLException e) {
      ce.add(e);
    }
  }
  if (null != ce.getCause()) {
    throw ce;
  }
}
