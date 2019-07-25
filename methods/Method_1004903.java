@SuppressWarnings("CloneDoesntCallSuperClone") @SuppressFBWarnings(value="CN_IDIOM_NO_SUPER_CALL",justification="Only inherits from Object") @Override public GlobalViewElementDefinition clone(){
  return new GlobalViewElementDefinition.Builder().json(toJson(false)).build();
}
