private static void readResidues(VorbisBitArray bitArray) throws ParserException {
  int residueCount=bitArray.readBits(6) + 1;
  for (int i=0; i < residueCount; i++) {
    int residueType=bitArray.readBits(16);
    if (residueType > 2) {
      throw new ParserException("residueType greater than 2 is not decodable");
    }
 else {
      bitArray.skipBits(24);
      bitArray.skipBits(24);
      bitArray.skipBits(24);
      int classifications=bitArray.readBits(6) + 1;
      bitArray.skipBits(8);
      int[] cascade=new int[classifications];
      for (int j=0; j < classifications; j++) {
        int highBits=0;
        int lowBits=bitArray.readBits(3);
        if (bitArray.readBit()) {
          highBits=bitArray.readBits(5);
        }
        cascade[j]=highBits * 8 + lowBits;
      }
      for (int j=0; j < classifications; j++) {
        for (int k=0; k < 8; k++) {
          if ((cascade[j] & (0x01 << k)) != 0) {
            bitArray.skipBits(8);
          }
        }
      }
    }
  }
}
