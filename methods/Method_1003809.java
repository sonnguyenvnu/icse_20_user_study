/** 
 * Creates a new transaction. <p> This method always creates a new transaction. It is more typical to use  {@link #get(Factory)} to use the existing transaction, or create a new one if none exists.
 * @param connectionFactory the connection factory
 * @return the newly created transaction
 */
static Transaction create(Factory<? extends Connection> connectionFactory){
  return new DefaultTransaction(connectionFactory);
}
