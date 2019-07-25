/** 
 * Convert the multi-dimensional value into a one-dimensional (scalar) value. This is done by interleaving the bits of the values. Each values must be between 0 (including) and the maximum value for the given number of dimensions (getMaxValue, excluding). To normalize values to this range, use the normalize function.
 * @param values the multi-dimensional value
 * @return the scalar value
 */
public long interleave(int... values){
  int dimensions=values.length;
  long max=getMaxValue(dimensions);
  int bitsPerValue=getBitsPerValue(dimensions);
  long x=0;
  for (int i=0; i < dimensions; i++) {
    long k=values[i];
    if (k < 0 || k > max) {
      throw new IllegalArgumentException(0 + "<" + k + "<" + max);
    }
    for (int b=0; b < bitsPerValue; b++) {
      x|=(k & (1L << b)) << (i + (dimensions - 1) * b);
    }
  }
  return x;
}
