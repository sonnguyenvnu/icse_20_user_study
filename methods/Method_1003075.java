/** 
 * Get the number of active rows in this undo log.
 * @return the number of rows
 */
int size(){
  return storedEntries + records.size();
}
