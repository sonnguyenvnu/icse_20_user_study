/** 
 * Prepares the query just after the initialization. Query is fully set and ready.
 */
protected void prepareQuery(){
  if (fetchSize != 0) {
    setFetchSize(fetchSize);
  }
  if (maxRows != 0) {
    setMaxRows(maxRows);
  }
}
