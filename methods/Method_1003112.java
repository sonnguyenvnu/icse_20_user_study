/** 
 * Closes this statement. All result sets that where created by this statement become invalid after calling this method.
 */
@Override public void close() throws SQLException {
  try {
    super.close();
    batchParameters=null;
    batchIdentities=null;
    if (command != null) {
      command.close();
      command=null;
    }
  }
 catch (  Exception e) {
    throw logAndConvert(e);
  }
}
