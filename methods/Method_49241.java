@Override public <O>O valueOrNull(PropertyKey key){
  if (key instanceof ImplicitKey)   return ((ImplicitKey)key).computeProperty(this);
  return null;
}
