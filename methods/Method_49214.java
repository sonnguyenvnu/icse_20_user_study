@Override public StandardPropertyKeyMaker dataType(Class<?> clazz){
  Preconditions.checkArgument(clazz != null,"Need to specify a data type");
  dataType=clazz;
  return this;
}
