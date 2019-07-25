/** 
 * Unbinds this transaction from the current execution. <p> If the transaction is not bound, this method is effectively a noop and returns false.
 * @return whether this transaction was actually bound
 * @throws TransactionException if a different transaction is bound to the execution
 * @see #bind()
 */
default boolean unbind(){
  Execution execution=Execution.current();
  Optional<Transaction> transaction=execution.maybeGet(Transaction.class);
  if (transaction.isPresent() && transaction.get() == this) {
    execution.remove(Transaction.class);
    return true;
  }
 else {
    return false;
  }
}
