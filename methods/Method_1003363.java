/** 
 * Get or create a geometry value for the given internal EWKB representation.
 * @param bytes the WKB representation of the geometry. May not be modified.
 * @return the value
 */
public static ValueGeometry get(byte[] bytes){
  return (ValueGeometry)Value.cache(new ValueGeometry(bytes,UNKNOWN_ENVELOPE));
}
