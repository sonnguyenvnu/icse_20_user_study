/** 
 * Checks if existing connection is valid and available. It may happens that if connection is not used for a while it becomes inactive, although not technically closed.
 */
private boolean isConnectionValid(final ConnectionData connectionData,final long now){
  if (!validateConnection) {
    return true;
  }
  if (now < connectionData.lastUsed + validationTimeout) {
    return true;
  }
  Connection conn=connectionData.connection;
  if (validationQuery == null) {
    try {
      return !conn.isClosed();
    }
 catch (    SQLException sex) {
      return false;
    }
  }
  boolean valid=true;
  Statement st=null;
  try {
    st=conn.createStatement();
    st.execute(validationQuery);
  }
 catch (  SQLException sex) {
    valid=false;
  }
 finally {
    if (st != null) {
      try {
        st.close();
      }
 catch (      SQLException ignore) {
      }
    }
  }
  return valid;
}
