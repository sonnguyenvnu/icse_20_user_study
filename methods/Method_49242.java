@Override public <O>O value(String key){
  if (!tx().containsPropertyKey(key))   throw Property.Exceptions.propertyDoesNotExist(this,key);
  O val=valueOrNull(tx().getPropertyKey(key));
  if (val == null)   throw Property.Exceptions.propertyDoesNotExist(this,key);
  return val;
}
