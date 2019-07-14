/** 
 * Unsafe version of  {@link #mName(ByteBuffer) mName}. 
 */
public static void nmName(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIImporterDesc.MNAME,memAddress(value));
}
