@Override public <A>A value(String key){
  throw Property.Exceptions.propertyDoesNotExist(this,key);
}
