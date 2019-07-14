/** 
 * Utility method that ensures the given value lies within the field's legal value range.
 * @param value  the value to fit into the wrapped value range
 * @param minValue the wrap range minimum value.
 * @param maxValue the wrap range maximum value.  This must begreater than minValue (checked by the method).
 * @return the wrapped value
 * @throws IllegalArgumentException if minValue is greaterthan or equal to maxValue
 */
public static int getWrappedValue(int value,int minValue,int maxValue){
  if (minValue >= maxValue) {
    throw new IllegalArgumentException("MIN > MAX");
  }
  int wrapRange=maxValue - minValue + 1;
  value-=minValue;
  if (value >= 0) {
    return (value % wrapRange) + minValue;
  }
  int remByRange=(-value) % wrapRange;
  if (remByRange == 0) {
    return 0 + minValue;
  }
  return (wrapRange - remByRange) + minValue;
}
