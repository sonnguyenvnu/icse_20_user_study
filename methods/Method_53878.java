/** 
 * Unsafe version of  {@link #mPosition}. 
 */
public static AIVector3D nmPosition(long struct){
  return AIVector3D.create(struct + AILight.MPOSITION);
}
