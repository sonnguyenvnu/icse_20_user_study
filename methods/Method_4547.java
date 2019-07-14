/** 
 * Returns the stream time in microseconds for a given stream position.
 * @param position The stream byte-position.
 * @param firstFrameBytePosition The position of the first frame in the stream.
 * @param bitrate The bitrate (which is assumed to be constant in the stream).
 * @return The stream time in microseconds for the given stream position.
 */
private static long getTimeUsAtPosition(long position,long firstFrameBytePosition,int bitrate){
  return Math.max(0,position - firstFrameBytePosition) * C.BITS_PER_BYTE * C.MICROS_PER_SECOND / bitrate;
}
