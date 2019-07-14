/** 
 * Unsafe version of  {@link #mUp}. 
 */
public static AIVector3D nmUp(long struct){
  return AIVector3D.create(struct + AILight.MUP);
}
