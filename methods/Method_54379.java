/** 
 * Unsafe version of  {@link #m_meshAssetFileName}. 
 */
public static ByteBuffer nm_meshAssetFileName(long struct){
  return memByteBuffer(struct + B3CollisionShapeData.M_MESHASSETFILENAME,VISUAL_SHAPE_MAX_PATH_LEN);
}
