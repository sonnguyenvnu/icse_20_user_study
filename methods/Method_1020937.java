private int first(byte[] in,int inPos){
  return (in[inPos] << 8) + (in[inPos + 1] & 255);
}
