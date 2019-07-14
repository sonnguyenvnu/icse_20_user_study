/** 
 * Not every marker is followed by associated segment
 */
private static boolean doesMarkerStartSegment(int markerSecondByte){
  if (markerSecondByte == JfifUtil.MARKER_TEM) {
    return false;
  }
  if (markerSecondByte >= JfifUtil.MARKER_RST0 && markerSecondByte <= JfifUtil.MARKER_RST7) {
    return false;
  }
  return markerSecondByte != JfifUtil.MARKER_EOI && markerSecondByte != JfifUtil.MARKER_SOI;
}
