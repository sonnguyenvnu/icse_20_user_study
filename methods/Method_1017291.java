/** 
 * Prepare local state changes for the supplied transaction. This method should persist any required information to ensure that it can undo (rollback) or make permanent (commit) the work done inside this transaction, when told to do so.
 * @param txID The transaction identifier
 * @return true on success, false otherwise
 */
public boolean prepare(Object txID){
  System.out.println("[SERVICE] prepare called on backend resource.");
  return true;
}
