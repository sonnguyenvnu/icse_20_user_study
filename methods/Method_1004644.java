@Override public Object apply(Object originalKey){
  if (originalKey == null) {
    return null;
  }
  if (originalKey instanceof String) {
    return originalKey;
  }
  return JSON.toJSONString(originalKey);
}
