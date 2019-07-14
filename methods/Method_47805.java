/** 
 * Executes the given callback inside a database transaction. <p> If the callback terminates without throwing any exceptions, the transaction is considered successful. If any exceptions are thrown, the transaction is aborted. Nesting transactions is not allowed.
 */
public void executeAsTransaction(Runnable callback){
  db.beginTransaction();
  try {
    callback.run();
    db.setTransactionSuccessful();
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
 finally {
    db.endTransaction();
  }
}
