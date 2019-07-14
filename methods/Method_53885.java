/** 
 * Unsafe version of  {@link #mSemantic}. 
 */
public static int nmSemantic(long struct){
  return UNSAFE.getInt(null,struct + AIMaterialProperty.MSEMANTIC);
}
