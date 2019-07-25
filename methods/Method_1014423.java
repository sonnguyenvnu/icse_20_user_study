/** 
 * Normalize value to the tradfri range.
 * @param value double in the range 0.0 to 1.0
 * @return normalized value in the range 0 to 65535
 */
private int normalize(double value){
  return (int)(value * 65535 + 0.5);
}
