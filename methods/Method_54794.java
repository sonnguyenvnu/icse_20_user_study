/** 
 * Unsafe version of  {@link #m_meshAssetFileName(ByteBuffer) m_meshAssetFileName}. 
 */
public static void nm_meshAssetFileName(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
    checkGT(value,VISUAL_SHAPE_MAX_PATH_LEN);
  }
  memCopy(memAddress(value),struct + B3VisualShapeData.M_MESHASSETFILENAME,value.remaining());
}
