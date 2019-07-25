/** 
 * Reverse-normalize value from the tradfri range.
 * @param value integer in the range 0 to 65535
 * @return unnormalized value in the range 0.0 to 1.0
 */
private float unnormalize(int value){
  return (value / 65535.0f);
}
