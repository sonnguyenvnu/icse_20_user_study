/** 
 * @return the euclidean length of the specified quaternion 
 */
public final static float len(final float x,final float y,final float z,final float w){
  return (float)Math.sqrt(x * x + y * y + z * z + w * w);
}
