@Override public <O>O getValueDirect(PropertyKey key){
  return getPropertyMap().get(key.longId());
}
