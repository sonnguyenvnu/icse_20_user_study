/** 
 * Limits the value to the given min and max range.
 */
private float limit(float value,float min,float max){
  return Math.min(Math.max(min,value),max);
}
