private ByteArrayInputStream cleanBuffer(byte[] data){
  byte[] output=new byte[data.length];
  int inPos=0;
  int outPos=0;
  while (inPos < data.length) {
    if (data[inPos] == 0 && data[inPos + 1] == 0 && data[inPos + 2] == 3) {
      output[outPos]=0;
      output[outPos + 1]=0;
      inPos+=3;
      outPos+=2;
    }
 else {
      output[outPos]=data[inPos];
      inPos++;
      outPos++;
    }
  }
  return new ByteArrayInputStream(output,0,outPos);
}
