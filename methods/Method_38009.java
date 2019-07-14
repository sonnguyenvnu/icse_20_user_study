/** 
 * Joins an array of objects into one string without separators.
 */
public static String join(final Object[] array){
  if (array == null) {
    return null;
  }
  if (array.length == 0) {
    return StringPool.EMPTY;
  }
  if (array.length == 1) {
    return String.valueOf(array[0]);
  }
  final StringBuilder sb=new StringBuilder(array.length * 16);
  for (int i=0; i < array.length; i++) {
    sb.append(array[i]);
  }
  return sb.toString();
}
