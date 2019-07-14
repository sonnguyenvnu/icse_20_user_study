/** 
 * Converts object into pretty string. All arrays are iterated.
 */
public static String toPrettyString(final Object value){
  if (value == null) {
    return StringPool.NULL;
  }
  final Class<?> type=value.getClass();
  if (type.isArray()) {
    final Class componentType=type.getComponentType();
    if (componentType.isPrimitive()) {
      final StringBuilder sb=new StringBuilder();
      sb.append('[');
      if (componentType == int.class) {
        sb.append(ArraysUtil.toString((int[])value));
      }
 else       if (componentType == long.class) {
        sb.append(ArraysUtil.toString((long[])value));
      }
 else       if (componentType == double.class) {
        sb.append(ArraysUtil.toString((double[])value));
      }
 else       if (componentType == float.class) {
        sb.append(ArraysUtil.toString((float[])value));
      }
 else       if (componentType == boolean.class) {
        sb.append(ArraysUtil.toString((boolean[])value));
      }
 else       if (componentType == short.class) {
        sb.append(ArraysUtil.toString((short[])value));
      }
 else       if (componentType == byte.class) {
        sb.append(ArraysUtil.toString((byte[])value));
      }
 else {
        throw new IllegalArgumentException();
      }
      sb.append(']');
      return sb.toString();
    }
 else {
      final StringBuilder sb=new StringBuilder();
      sb.append('[');
      final Object[] array=(Object[])value;
      for (int i=0; i < array.length; i++) {
        if (i > 0) {
          sb.append(',');
        }
        sb.append(toPrettyString(array[i]));
      }
      sb.append(']');
      return sb.toString();
    }
  }
 else   if (value instanceof Iterable) {
    final Iterable iterable=(Iterable)value;
    final StringBuilder sb=new StringBuilder();
    sb.append('{');
    int i=0;
    for (    final Object o : iterable) {
      if (i > 0) {
        sb.append(',');
      }
      sb.append(toPrettyString(o));
      i++;
    }
    sb.append('}');
    return sb.toString();
  }
  return value.toString();
}
