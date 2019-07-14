/** 
 * Unsafe version of  {@link #mPrimitiveTypes(int) mPrimitiveTypes}. 
 */
public static void nmPrimitiveTypes(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MPRIMITIVETYPES,value);
}
