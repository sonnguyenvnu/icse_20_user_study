@Override public <O>O valueOrNull(PropertyKey key){
  verifyAccess();
  if (key instanceof ImplicitKey) {
    return ((ImplicitKey)key).computeProperty(this);
  }
  return it().getValueDirect(key);
}
