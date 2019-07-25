@SuppressWarnings("CloneDoesntCallSuperClone") @SuppressFBWarnings(value="CN_IDIOM_NO_SUPER_CALL",justification="Uses toJson instead.") @Override public Schema clone(){
  return fromJson(toJson(false));
}
