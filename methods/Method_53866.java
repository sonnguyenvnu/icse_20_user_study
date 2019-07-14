/** 
 * Unsafe version of  {@link #mAuthor(ByteBuffer) mAuthor}. 
 */
public static void nmAuthor(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIImporterDesc.MAUTHOR,memAddress(value));
}
