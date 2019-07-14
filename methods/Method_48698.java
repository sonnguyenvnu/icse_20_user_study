@Override public boolean validDataType(Class datatype){
  return handlers.containsKey(normalizeDataType(datatype));
}
