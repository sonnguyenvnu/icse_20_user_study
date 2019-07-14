/** 
 * Converts value to <code>int</code>. Returns default value when conversion result is <code>null</code>.
 */
public int toIntValue(final Object value,final int defaultValue){
  final Integer result=toInteger(value);
  if (result == null) {
    return defaultValue;
  }
  return result.intValue();
}
