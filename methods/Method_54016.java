/** 
 * Unsafe version of  {@link #mMaterialIndex(int) mMaterialIndex}. 
 */
public static void nmMaterialIndex(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MMATERIALINDEX,value);
}
