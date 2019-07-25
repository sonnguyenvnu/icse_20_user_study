protected static byte[] pad(byte[] input,int blockSize){
  int nBlocks=(input.length + blockSize - 1) / blockSize;
  return Arrays.copyOfRange(input,0,nBlocks * blockSize);
}
