/** 
 * Unsafe version of  {@link #fileExtension(ByteBuffer) fileExtension}. 
 */
public static void nfileExtension(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIExportFormatDesc.FILEEXTENSION,memAddress(value));
}
