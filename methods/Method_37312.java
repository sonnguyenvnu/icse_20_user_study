/** 
 * Converts value to <code>double[]</code>.
 */
public double[] toDoubleArray(final Object value){
  final TypeConverter<double[]> tc=TypeConverterManager.get().lookup(double[].class);
  return tc.convert(value);
}
