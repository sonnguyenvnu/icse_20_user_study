/** 
 * Converts value to <code>short[]</code>.
 */
public short[] toShortArray(final Object value){
  final TypeConverter<short[]> tc=TypeConverterManager.get().lookup(short[].class);
  return tc.convert(value);
}
