@Override public void applySpecification(final ExchangeSpecification specification){
  if (specification.getPassword() == null) {
    throw new IllegalStateException("password must be set to enable the wallet's RPC interface");
  }
  super.applySpecification(specification);
}
