/** 
 * Converts value to <code>Integer</code>.
 */
public Integer toInteger(final Object value){
  final TypeConverter<Integer> tc=TypeConverterManager.get().lookup(Integer.class);
  return tc.convert(value);
}
