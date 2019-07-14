/** 
 * Converts value to <code>Byte</code>.
 */
public Byte toByte(final Object value){
  final TypeConverter<Byte> tc=TypeConverterManager.get().lookup(Byte.class);
  return tc.convert(value);
}
