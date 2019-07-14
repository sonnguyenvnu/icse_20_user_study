/** 
 * Converts value to <code>String[]</code>.
 */
public String[] toStringArray(final Object value){
  final TypeConverter<String[]> tc=TypeConverterManager.get().lookup(String[].class);
  return tc.convert(value);
}
