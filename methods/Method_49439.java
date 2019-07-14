@Override public BaseTransactionConfigurable beginTransaction(BaseTransactionConfig config) throws BackendException {
  return new DefaultTransaction(config);
}
