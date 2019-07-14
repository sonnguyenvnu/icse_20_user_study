/** 
 * Returns <code>ConnectionProvider</code> instance. Instance will be registered into the Petite context.
 */
protected ConnectionProvider createConnectionProviderIfNotSupplied(){
  if (connectionProviderSupplier != null) {
    return connectionProviderSupplier.get();
  }
  return new CoreConnectionPool();
}
