/** 
 * Cleanup helper method that closes the given <code>ResultSet</code> while ignoring any errors.
 */
protected static void closeResultSet(ResultSet rs){
  if (null != rs) {
    try {
      rs.close();
    }
 catch (    SQLException ignore) {
    }
  }
}
