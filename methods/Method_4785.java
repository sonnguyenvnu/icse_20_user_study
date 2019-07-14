private int parsePayloadLengthInfo(ParsableBitArray data) throws ParserException {
  int muxSlotLengthBytes=0;
  if (frameLengthType == 0) {
    int tmp;
    do {
      tmp=data.readBits(8);
      muxSlotLengthBytes+=tmp;
    }
 while (tmp == 255);
    return muxSlotLengthBytes;
  }
 else {
    throw new ParserException();
  }
}
