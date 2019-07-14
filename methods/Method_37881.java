/** 
 * Creates new instances including for common mutable classes that do not have a default constructor. more user-friendly. It examines if class is a map, list, String, Character, Boolean or a Number. Immutable instances are cached and not created again. Arrays are also created with no elements. Note that this bunch of <code>if</code> blocks is faster then using a <code>HashMap</code>.
 */
@SuppressWarnings("unchecked") public static <T>T newInstance(final Class<T> type) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
  if (type.isPrimitive()) {
    if (type == int.class) {
      return (T)Integer.valueOf(0);
    }
    if (type == long.class) {
      return (T)Long.valueOf(0);
    }
    if (type == boolean.class) {
      return (T)Boolean.FALSE;
    }
    if (type == float.class) {
      return (T)Float.valueOf(0);
    }
    if (type == double.class) {
      return (T)Double.valueOf(0);
    }
    if (type == byte.class) {
      return (T)Byte.valueOf((byte)0);
    }
    if (type == short.class) {
      return (T)Short.valueOf((short)0);
    }
    if (type == char.class) {
      return (T)Character.valueOf((char)0);
    }
    throw new IllegalArgumentException("Invalid primitive: " + type);
  }
  if (type.getName().startsWith("java.")) {
    if (type == Integer.class) {
      return (T)Integer.valueOf(0);
    }
    if (type == String.class) {
      return (T)StringPool.EMPTY;
    }
    if (type == Long.class) {
      return (T)Long.valueOf(0);
    }
    if (type == Boolean.class) {
      return (T)Boolean.FALSE;
    }
    if (type == Float.class) {
      return (T)Float.valueOf(0);
    }
    if (type == Double.class) {
      return (T)Double.valueOf(0);
    }
    if (type == Map.class) {
      return (T)new HashMap();
    }
    if (type == List.class) {
      return (T)new ArrayList();
    }
    if (type == Set.class) {
      return (T)new HashSet();
    }
    if (type == Collection.class) {
      return (T)new ArrayList();
    }
    if (type == Byte.class) {
      return (T)Byte.valueOf((byte)0);
    }
    if (type == Short.class) {
      return (T)Short.valueOf((short)0);
    }
    if (type == Character.class) {
      return (T)Character.valueOf((char)0);
    }
  }
  if (type.isEnum()) {
    return type.getEnumConstants()[0];
  }
  if (type.isArray()) {
    return (T)Array.newInstance(type.getComponentType(),0);
  }
  Constructor<T> declaredConstructor=type.getDeclaredConstructor();
  forceAccess(declaredConstructor);
  return declaredConstructor.newInstance();
}
