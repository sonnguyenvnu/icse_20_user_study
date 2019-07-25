public static <T>boolean contains(Class<T> clazz){
  if (PRIMITIVES.contains(clazz) || isAJavaPrimitiveArray(clazz))   return true;
  for (  Class<?> primitive : PRIMITIVES) {
    if (primitive.isAssignableFrom(clazz)) {
      return true;
    }
  }
  return false;
}
