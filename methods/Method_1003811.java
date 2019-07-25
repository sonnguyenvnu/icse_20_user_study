/** 
 * Binds this transaction to the current execution. <p> The instance is added to the current execution's registry. <p> It is typically not necessary to call this directly. Transactions default to “auto binding”. That is, this method is called implicitly when the transaction starts.
 * @return {@code this}
 * @throws TransactionException if a different transaction is bound to the execution
 */
default Transaction bind() throws TransactionException {
  Execution execution=Execution.current();
  execution.maybeGet(Transaction.class).ifPresent(t -> {
    if (t != this) {
      throw new TransactionException("A transaction is already bound");
    }
  }
);
  execution.add(Transaction.class,this);
  return this;
}
