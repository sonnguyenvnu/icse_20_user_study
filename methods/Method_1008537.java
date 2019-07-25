/** 
 * In case of format field, we can parse the from/to fields using this time format
 */
public RangeQueryBuilder format(String format){
  if (format == null) {
    throw new IllegalArgumentException("format cannot be null");
  }
  this.format=Joda.forPattern(format);
  return this;
}
