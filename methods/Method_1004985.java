@SuppressWarnings("CloneDoesntCallSuperClone") @SuppressFBWarnings(value="CN_IDIOM_NO_SUPER_CALL",justification="Only inherits from Object") @Override public StoreProperties clone(){
  return StoreProperties.loadStoreProperties((Properties)getProperties().clone());
}
