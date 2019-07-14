/** 
 * Converts value to <code>char[]</code>.
 */
public char[] toCharacterArray(final Object value){
  final TypeConverter<char[]> tc=TypeConverterManager.get().lookup(char[].class);
  return tc.convert(value);
}
