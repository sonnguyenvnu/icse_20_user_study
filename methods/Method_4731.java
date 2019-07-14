/** 
 * Locates the next syncword, advancing the position to the byte that immediately follows it. If a syncword was not located, the position is advanced to the limit.
 * @param pesBuffer The buffer whose position should be advanced.
 * @return Whether a syncword position was found.
 */
private boolean skipToNextSync(ParsableByteArray pesBuffer){
  while (pesBuffer.bytesLeft() > 0) {
    if (!lastByteWas0B) {
      lastByteWas0B=pesBuffer.readUnsignedByte() == 0x0B;
      continue;
    }
    int secondByte=pesBuffer.readUnsignedByte();
    if (secondByte == 0x77) {
      lastByteWas0B=false;
      return true;
    }
 else {
      lastByteWas0B=secondByte == 0x0B;
    }
  }
  return false;
}
