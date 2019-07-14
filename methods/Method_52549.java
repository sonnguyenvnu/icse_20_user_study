@Override public boolean isClassOrInterface(){
  return !clazz.isEnum() && !clazz.isPrimitive() && !clazz.isAnnotation() && !clazz.isArray();
}
