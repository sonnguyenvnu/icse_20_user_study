/** 
 * Unsafe version of  {@link #id(ByteBuffer) id}. 
 */
public static void nid(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIExportFormatDesc.ID,memAddress(value));
}
