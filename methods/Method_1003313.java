/** 
 * Gets one of the original multi-dimensional values from a scalar value.
 * @param dimensions the number of dimensions
 * @param scalar the scalar value
 * @param dim the dimension of the returned value (starting from 0)
 * @return the value
 */
public int deinterleave(int dimensions,long scalar,int dim){
  int bitsPerValue=getBitsPerValue(dimensions);
  int value=0;
  for (int i=0; i < bitsPerValue; i++) {
    value|=(scalar >> (dim + (dimensions - 1) * i)) & (1L << i);
  }
  return value;
}
