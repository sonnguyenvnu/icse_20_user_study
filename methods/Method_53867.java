/** 
 * Unsafe version of  {@link #mMaintainer(ByteBuffer) mMaintainer}. 
 */
public static void nmMaintainer(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + AIImporterDesc.MMAINTAINER,memAddress(value));
}
