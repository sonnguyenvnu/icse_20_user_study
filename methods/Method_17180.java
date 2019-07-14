private long newBufferAndOffset(E[] nextBuffer,final long index){
  consumerBuffer=nextBuffer;
  consumerMask=(nextBuffer.length - 2L) << 1;
  final long offsetInNew=modifiedCalcElementOffset(index,consumerMask);
  return offsetInNew;
}
