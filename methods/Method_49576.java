/** 
 * Deletes the specified table with all its columns. ATTENTION: Invoking this method will delete the table if it exists and therefore causes data loss.
 */
@Override public void clearStorage() throws BackendException {
  try (AdminMask adm=getAdminInterface()){
    if (this.storageConfig.get(DROP_ON_CLEAR)) {
      adm.dropTable(tableName);
    }
 else {
      adm.clearTable(tableName,times.getTime(times.getTime()));
    }
  }
 catch (  IOException e) {
    throw new TemporaryBackendException(e);
  }
}
