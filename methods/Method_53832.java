/** 
 * Unsafe version of  {@link #description(ByteBuffer) description}. 
 */
public static void ndescription(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIExportFormatDesc.DESCRIPTION,memAddress(value));
}
