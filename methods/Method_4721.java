private static CodeBook readBook(VorbisBitArray bitArray) throws ParserException {
  if (bitArray.readBits(24) != 0x564342) {
    throw new ParserException("expected code book to start with [0x56, 0x43, 0x42] at " + bitArray.getPosition());
  }
  int dimensions=bitArray.readBits(16);
  int entries=bitArray.readBits(24);
  long[] lengthMap=new long[entries];
  boolean isOrdered=bitArray.readBit();
  if (!isOrdered) {
    boolean isSparse=bitArray.readBit();
    for (int i=0; i < lengthMap.length; i++) {
      if (isSparse) {
        if (bitArray.readBit()) {
          lengthMap[i]=(long)(bitArray.readBits(5) + 1);
        }
 else {
          lengthMap[i]=0;
        }
      }
 else {
        lengthMap[i]=(long)(bitArray.readBits(5) + 1);
      }
    }
  }
 else {
    int length=bitArray.readBits(5) + 1;
    for (int i=0; i < lengthMap.length; ) {
      int num=bitArray.readBits(iLog(entries - i));
      for (int j=0; j < num && i < lengthMap.length; i++, j++) {
        lengthMap[i]=length;
      }
      length++;
    }
  }
  int lookupType=bitArray.readBits(4);
  if (lookupType > 2) {
    throw new ParserException("lookup type greater than 2 not decodable: " + lookupType);
  }
 else   if (lookupType == 1 || lookupType == 2) {
    bitArray.skipBits(32);
    bitArray.skipBits(32);
    int valueBits=bitArray.readBits(4) + 1;
    bitArray.skipBits(1);
    long lookupValuesCount;
    if (lookupType == 1) {
      if (dimensions != 0) {
        lookupValuesCount=mapType1QuantValues(entries,dimensions);
      }
 else {
        lookupValuesCount=0;
      }
    }
 else {
      lookupValuesCount=(long)entries * dimensions;
    }
    bitArray.skipBits((int)(lookupValuesCount * valueBits));
  }
  return new CodeBook(dimensions,entries,lengthMap,lookupType,isOrdered);
}
