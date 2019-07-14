/** 
 * Unsafe version of  {@link #m_worldLinkFrameOrientation(DoubleBuffer) m_worldLinkFrameOrientation}. 
 */
public static void nm_worldLinkFrameOrientation(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDLINKFRAMEORIENTATION,value.remaining() * 8);
}
