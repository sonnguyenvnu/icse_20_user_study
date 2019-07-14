/** 
 * Unsafe version of  {@link #m_localVisualFrame(DoubleBuffer) m_localVisualFrame}. 
 */
public static void nm_localVisualFrame(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,7);
  }
  memCopy(memAddress(value),struct + B3VisualShapeData.M_LOCALVISUALFRAME,value.remaining() * 8);
}
