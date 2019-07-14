@Override public TransactionBuilder consistencyChecks(boolean enabled){
  this.verifyUniqueness=enabled;
  this.acquireLocks=enabled;
  return this;
}
