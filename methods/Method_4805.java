private static boolean checkMarkerBits(byte[] scrBytes){
  if ((scrBytes[0] & 0xC4) != 0x44) {
    return false;
  }
  if ((scrBytes[2] & 0x04) != 0x04) {
    return false;
  }
  if ((scrBytes[4] & 0x04) != 0x04) {
    return false;
  }
  if ((scrBytes[5] & 0x01) != 0x01) {
    return false;
  }
  return (scrBytes[8] & 0x03) == 0x03;
}
