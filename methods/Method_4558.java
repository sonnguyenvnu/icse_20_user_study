@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  input.peekFully(scratch.data,0,3);
  scratch.setPosition(0);
  if (scratch.readUnsignedInt24() != FLV_TAG) {
    return false;
  }
  input.peekFully(scratch.data,0,2);
  scratch.setPosition(0);
  if ((scratch.readUnsignedShort() & 0xFA) != 0) {
    return false;
  }
  input.peekFully(scratch.data,0,4);
  scratch.setPosition(0);
  int dataOffset=scratch.readInt();
  input.resetPeekPosition();
  input.advancePeekPosition(dataOffset);
  input.peekFully(scratch.data,0,4);
  scratch.setPosition(0);
  return scratch.readInt() == 0;
}
