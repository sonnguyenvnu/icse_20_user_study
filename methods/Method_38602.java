@Override public JoyDb withConnectionProvider(final Supplier<ConnectionProvider> connectionProviderSupplier){
  requireNotStarted(connectionProvider);
  this.connectionProviderSupplier=connectionProviderSupplier;
  return this;
}
