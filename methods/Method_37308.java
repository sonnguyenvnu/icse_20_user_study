/** 
 * Converts value to <code>char</code>. Returns default value when conversion result is <code>null</code>.
 */
public char toCharValue(final Object value,final char defaultValue){
  final Character result=toCharacter(value);
  if (result == null) {
    return defaultValue;
  }
  return result.charValue();
}
