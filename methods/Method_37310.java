/** 
 * Converts value to <code>int[]</code>.
 */
public int[] toIntegerArray(final Object value){
  final TypeConverter<int[]> tc=TypeConverterManager.get().lookup(int[].class);
  return tc.convert(value);
}
