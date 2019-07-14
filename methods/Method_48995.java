@Override public <O>O value(String key){
  verifyAccess();
  O val=valueInternal(tx().getPropertyKey(key));
  if (val == null) {
    throw Property.Exceptions.propertyDoesNotExist(this,key);
  }
  return val;
}
