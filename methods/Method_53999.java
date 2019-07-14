/** 
 * Unsafe version of  {@link #mMaterialIndex}. 
 */
public static int nmMaterialIndex(long struct){
  return UNSAFE.getInt(null,struct + AIMesh.MMATERIALINDEX);
}
