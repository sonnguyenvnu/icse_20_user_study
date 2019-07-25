@Override public boolean has(Class<?> cls){
  if (_annotations == null) {
    return false;
  }
  return _annotations.containsKey(cls);
}
