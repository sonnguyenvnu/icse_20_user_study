/** 
 * Unsafe version of  {@link #m_meshAssetFileNameString}. 
 */
public static String nm_meshAssetFileNameString(long struct){
  return memASCII(struct + B3VisualShapeData.M_MESHASSETFILENAME);
}
