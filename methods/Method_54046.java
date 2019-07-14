/** 
 * Unsafe version of  {@link #mData(ByteBuffer) mData}. 
 */
public static void nmData(long struct,ByteBuffer value){
  memPutAddress(struct + AIMetaDataEntry.MDATA,memAddress(value));
}
