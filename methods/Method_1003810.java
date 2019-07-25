/** 
 * Returns the current transaction if present
 * @return the current transaction if present
 * @throws TransactionException if there is no bound transaction
 */
static Transaction join() throws TransactionException {
  return current().orElseThrow(() -> new TransactionException("There is no bound transaction to join"));
}
