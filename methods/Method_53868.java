/** 
 * Unsafe version of  {@link #mComments(ByteBuffer) mComments}. 
 */
public static void nmComments(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIImporterDesc.MCOMMENTS,memAddress(value));
}
