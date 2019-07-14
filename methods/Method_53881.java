/** 
 * Unsafe version of  {@link #mSize}. 
 */
public static AIVector2D nmSize(long struct){
  return AIVector2D.create(struct + AILight.MSIZE);
}
