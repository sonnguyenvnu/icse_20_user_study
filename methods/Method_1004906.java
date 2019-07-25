@SuppressWarnings("CloneDoesntCallSuperClone") @SuppressFBWarnings(value="CN_IDIOM_NO_SUPER_CALL",justification="Only inherits from Object") @Override public ViewElementDefinition clone(){
  return new ViewElementDefinition.Builder().json(toJson(false)).build();
}
