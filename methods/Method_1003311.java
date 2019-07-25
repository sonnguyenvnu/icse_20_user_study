/** 
 * Normalize a value so that it is between the minimum and maximum for the given number of dimensions.
 * @param dimensions the number of dimensions
 * @param value the value (must be in the range min..max)
 * @param min the minimum value
 * @param max the maximum value (must be larger than min)
 * @return the normalized value in the range 0..getMaxValue(dimensions)
 */
public int normalize(int dimensions,double value,double min,double max){
  if (value < min || value > max) {
    throw new IllegalArgumentException(min + "<" + value + "<" + max);
  }
  double x=(value - min) / (max - min);
  return (int)(x * getMaxValue(dimensions));
}
