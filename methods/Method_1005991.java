private static void decompress(ByteArray input,int index,ByteArray output) throws FormatException {
  int[] bitIndex=new int[1];
  bitIndex[0]=index * 8;
  while (inflateBlock(input,bitIndex,output)) {
  }
}
