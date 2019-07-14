/** 
 * Unsafe version of  {@link #mFileExtensions(ByteBuffer) mFileExtensions}. 
 */
public static void nmFileExtensions(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIImporterDesc.MFILEEXTENSIONS,memAddress(value));
}
