/** 
 * Unsafe version of  {@link #mValues(IntBuffer) mValues}. 
 */
public static void nmValues(long struct,IntBuffer value){
  memPutAddress(struct + AIMeshMorphKey.MVALUES,memAddress(value));
}
