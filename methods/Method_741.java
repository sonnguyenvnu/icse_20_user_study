public boolean isExternalClass(Class<?> clazz){
  ClassLoader classLoader=clazz.getClassLoader();
  if (classLoader == null) {
    return false;
  }
  ClassLoader current=this;
  while (current != null) {
    if (current == classLoader) {
      return false;
    }
    current=current.getParent();
  }
  return true;
}
