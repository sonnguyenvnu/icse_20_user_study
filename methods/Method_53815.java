/** 
 * Unsafe version of  {@link #data(ByteBuffer) data}. 
 */
public static void ndata(long struct,ByteBuffer value){
  memPutAddress(struct + AIExportDataBlob.DATA,memAddress(value));
  nsize(struct,value.remaining());
}
