public void incrementBufferHead(){
  if (bufferHead == maxSentenceSize)   bufferHead=-1;
 else   bufferHead++;
}
