/** 
 * Populates the holder with data from an MP3 Xing header, if valid and non-zero.
 * @param value The 24-bit value to decode.
 * @return Whether the holder was populated.
 */
public boolean setFromXingHeaderValue(int value){
  int encoderDelay=value >> 12;
  int encoderPadding=value & 0x0FFF;
  if (encoderDelay > 0 || encoderPadding > 0) {
    this.encoderDelay=encoderDelay;
    this.encoderPadding=encoderPadding;
    return true;
  }
  return false;
}
