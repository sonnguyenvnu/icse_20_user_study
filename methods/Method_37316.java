/** 
 * Converts value to <code>Class[]</code>.
 */
public Class[] toClassArray(final Object value){
  final TypeConverter<Class[]> tc=TypeConverterManager.get().lookup(Class[].class);
  return tc.convert(value);
}
