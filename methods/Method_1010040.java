@NotNull public static Class<?> box(@NotNull Class<?> primitiveType){
  assert primitiveType.isPrimitive();
  return PRIMITIVE_TO_BOXED_TYPE.get(primitiveType);
}
