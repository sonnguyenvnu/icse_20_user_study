/** 
 * Unsafe version of  {@link #m_worldLinkFramePosition(DoubleBuffer) m_worldLinkFramePosition}. 
 */
public static void nm_worldLinkFramePosition(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3LinkState.M_WORLDLINKFRAMEPOSITION,value.remaining() * 8);
}
