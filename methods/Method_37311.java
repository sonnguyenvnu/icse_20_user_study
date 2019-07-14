/** 
 * Converts value to <code>long[]</code>.
 */
public long[] toLongArray(final Object value){
  final TypeConverter<long[]> tc=TypeConverterManager.get().lookup(long[].class);
  return tc.convert(value);
}
