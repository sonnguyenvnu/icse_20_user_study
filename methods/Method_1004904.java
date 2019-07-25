@SuppressWarnings("CloneDoesntCallSuperClone") @SuppressFBWarnings(value="CN_IDIOM_NO_SUPER_CALL",justification="Only inherits from Object") @Override public View clone(){
  return fromJson(toJson(false));
}
