/** 
 * Unsafe version of  {@link #mPrimitiveTypes}. 
 */
public static int nmPrimitiveTypes(long struct){
  return UNSAFE.getInt(null,struct + AIMesh.MPRIMITIVETYPES);
}
