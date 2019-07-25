/** 
 * Mark the transaction as committed, so that the modification counter of the database is incremented.
 */
public void commit(){
  if (database != null) {
    syncLastModificationIdWithDatabase();
  }
}
