/** 
 * Unsafe version of  {@link #mDirection}. 
 */
public static AIVector3D nmDirection(long struct){
  return AIVector3D.create(struct + AILight.MDIRECTION);
}
