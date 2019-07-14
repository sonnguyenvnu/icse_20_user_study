private static void copyLiteral(byte[] input,int ipIndex,byte[] output,int opIndex,int length) throws CorruptionException {
  assert length > 0;
  assert ipIndex >= 0;
  assert opIndex >= 0;
  int spaceLeft=output.length - opIndex;
  int readableBytes=input.length - ipIndex;
  if (readableBytes < length || spaceLeft < length) {
    throw new CorruptionException("Corrupt literal length");
  }
  if (length <= 16 && spaceLeft >= 16 && readableBytes >= 16) {
    copyLong(input,ipIndex,output,opIndex);
    copyLong(input,ipIndex + 8,output,opIndex + 8);
  }
 else {
    int fastLength=length & 0xFFFFFFF8;
    if (fastLength <= 64) {
      for (int i=0; i < fastLength; i+=8) {
        copyLong(input,ipIndex + i,output,opIndex + i);
      }
      int slowLength=length & 0x7;
      for (int i=0; i < slowLength; i+=1) {
        output[opIndex + fastLength + i]=input[ipIndex + fastLength + i];
      }
    }
 else {
      SnappyInternalUtils.copyMemory(input,ipIndex,output,opIndex,length);
    }
  }
}
