/** 
 * Converts a Windows buffer to an int.
 * @param buf buffer
 * @return int
 */
private static int convertBufferToInt(byte[] buf){
  return ((buf[0] & 0xff) + ((buf[1] & 0xff) << 8) + ((buf[2] & 0xff) << 16) + ((buf[3] & 0xff) << 24));
}
