@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  byte[] scratch=new byte[14];
  input.peekFully(scratch,0,14);
  if (PACK_START_CODE != (((scratch[0] & 0xFF) << 24) | ((scratch[1] & 0xFF) << 16) | ((scratch[2] & 0xFF) << 8) | (scratch[3] & 0xFF))) {
    return false;
  }
  if ((scratch[4] & 0xC4) != 0x44) {
    return false;
  }
  if ((scratch[6] & 0x04) != 0x04) {
    return false;
  }
  if ((scratch[8] & 0x04) != 0x04) {
    return false;
  }
  if ((scratch[9] & 0x01) != 0x01) {
    return false;
  }
  if ((scratch[12] & 0x03) != 0x03) {
    return false;
  }
  int packStuffingLength=scratch[13] & 0x07;
  input.advancePeekPosition(packStuffingLength);
  input.peekFully(scratch,0,3);
  return (PACKET_START_CODE_PREFIX == (((scratch[0] & 0xFF) << 16) | ((scratch[1] & 0xFF) << 8) | (scratch[2] & 0xFF)));
}
