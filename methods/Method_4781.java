/** 
 * Parses an AudioMuxElement as defined in 14496-3:2009, Section 1.7.3.1, Table 1.41.
 * @param data A {@link ParsableBitArray} containing the AudioMuxElement's bytes.
 */
private void parseAudioMuxElement(ParsableBitArray data) throws ParserException {
  boolean useSameStreamMux=data.readBit();
  if (!useSameStreamMux) {
    streamMuxRead=true;
    parseStreamMuxConfig(data);
  }
 else   if (!streamMuxRead) {
    return;
  }
  if (audioMuxVersionA == 0) {
    if (numSubframes != 0) {
      throw new ParserException();
    }
    int muxSlotLengthBytes=parsePayloadLengthInfo(data);
    parsePayloadMux(data,muxSlotLengthBytes);
    if (otherDataPresent) {
      data.skipBits((int)otherDataLenBits);
    }
  }
 else {
    throw new ParserException();
  }
}
