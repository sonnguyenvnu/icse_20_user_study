@Override public <T>List<T> asList(Class<T> type){
  return convertHandler.convertList(this,type);
}
