/** 
 * Checks if query is created (and not yet initialized or closed) and throws an exception if it is not.
 */
protected void checkCreated(){
  if (queryState != CREATED) {
    final String message=(queryState == INITIALIZED ? "Query is already initialized." : "Query is closed.");
    throw new DbSqlException(this,message + " Operation may be performed only on created queries.");
  }
}
