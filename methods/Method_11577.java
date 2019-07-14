public <T>List<T> toList(Class<T> clazz){
  if (getFirstSourceText() == null) {
    return null;
  }
  return JSON.parseArray(getFirstSourceText(),clazz);
}
